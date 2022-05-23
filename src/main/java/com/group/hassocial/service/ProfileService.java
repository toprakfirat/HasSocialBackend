package com.group.hassocial.service;

import com.group.hassocial.data.model.Photo;
import com.group.hassocial.data.model.User;
import com.group.hassocial.repository.GalleryRepository;
import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.util.UserUtil;
import com.hassocial.swaggergen.model.BaseUser;
import com.hassocial.swaggergen.model.ChangeAvatarRequest;
import com.hassocial.swaggergen.model.ChangeSettingsRequest;
import com.hassocial.swaggergen.model.ModifyGalleryRequest;
import com.hassocial.swaggergen.model.ProfileResponse;
import com.hassocial.swaggergen.model.Response;
import com.hassocial.swaggergen.model.Result;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Service
public class ProfileService {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private InterestsService interestsService;

    public ProfileResponse getProfile() {

        final ProfileResponse profileResponse = new ProfileResponse();

        //TODO Get UserId from session constant after it's implementation
        final int id = 1;

        final BaseUser user = userUtil.userToBaseUser(userRepository.findById(id).get());

        profileResponse.setResult(Result.OK);
        profileResponse.setUser(user);
        profileResponse.setInterests(interestsService.getUserInterests(id));

        return profileResponse;
    }

    @SneakyThrows
    public Response changeSettings(final ChangeSettingsRequest request) {

        //TODO Get UserId from session constant after it's implementation
        final int id = 1;
        final BaseUser user = userUtil.userToBaseUser(userRepository.findById(id).get());
        final BaseUser requestedUser = request.getUser();

        String fullName = user.getFullName();
        String birthDate = user.getBirthDate();
        Boolean gender = user.getGender();
        Integer orientation = user.getOrientation();

        if (!Objects.isNull(requestedUser.getFullName()) && !requestedUser.getFullName().equals(user.getFullName())) {
            fullName = requestedUser.getFullName();
        }
        if (!Objects.isNull(requestedUser.getBirthDate()) && !requestedUser.getBirthDate().equals(user.getBirthDate())) {
            birthDate = requestedUser.getBirthDate();
        }
        if (!Objects.isNull(requestedUser.getGender()) && !requestedUser.getGender().equals(user.getGender())) {
            gender = requestedUser.getGender();
        }
        if (!Objects.isNull(requestedUser.getOrientation()) && !requestedUser.getOrientation().equals(user.getOrientation())) {
            orientation = requestedUser.getOrientation();
        }

        final User updatedUser = userUtil.baseUserToUser(user);

        userRepository.updateUser(fullName, new SimpleDateFormat(DATE_FORMAT).parse(birthDate), gender, orientation, id);

        final Response response = new Response();
        response.setResult(Result.OK);

        return response;
    }

    public Response changeAvatar(final ChangeAvatarRequest changeAvatarRequest) {

        //TODO Get UserId from session constant after it's implementation
        final int id = 1;
        final int avatarImageID = userRepository.findById(id).get().getAvatarImageID();
        final int newAvatarID = changeAvatarRequest.getImageId().intValue();

        galleryRepository.updateAvatar(false, avatarImageID);
        galleryRepository.updateAvatar(true, newAvatarID);
        userRepository.updateAvatar(newAvatarID, id);

        final Response response = new Response();
        response.setResult(Result.OK);

        return response;
    }

    public Response modifyGallery(final ModifyGalleryRequest modifyGalleryRequest) {

        //TODO Get UserId from session constant after it's implementation
        final int id = 1;

        final List<String> uploadedPhotos = modifyGalleryRequest.getUploadedPhotos();
        final List<Integer> deletedPhotos = modifyGalleryRequest.getDeletedPhotos();

        if (uploadedPhotos.size() > 0 && uploadedPhotos.size() < 5) {
            uploadedPhotos.stream().forEach(uploadedPhoto -> {
                final Photo photo = new Photo();
                photo.setImage(uploadedPhoto);
                photo.setIsAvatarImage(false);
                photo.setUserID(id);
                galleryRepository.save(photo);
            });
        }

        if (deletedPhotos.size() > 0 && deletedPhotos.size() < 5) {
            deletedPhotos.stream().forEach(deletedPhoto -> {
                galleryRepository.deleteById(deletedPhoto);
            });
        }

        final Response response = new Response();
        response.setResult(Result.OK);

        return response;
    }
}
