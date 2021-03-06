package dev.willingapp.willing.controllers;

import com.sun.xml.bind.v2.TODO;
import dev.willingapp.willing.models.Album;
import dev.willingapp.willing.models.Image;
import dev.willingapp.willing.models.Item;
import dev.willingapp.willing.models.User;
import dev.willingapp.willing.repositories.AlbumRepository;
import dev.willingapp.willing.repositories.ImageRepository;
import dev.willingapp.willing.repositories.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Controller
//public class AlbumController {
//    private final AlbumRepository albumsDao;
//    private final UserRepository usersDao;
//
//    public AlbumController(AlbumRepository albumsDao, UserRepository usersDao) {
//        this.albumsDao = albumsDao;
//        this.usersDao = usersDao;
//    }
//
//    @GetMapping("/albums")
//    public String redirect() {
//        return "redirect:/albums";
//    }
//
//    // JSON - one post
//    @GetMapping("/albums/json/{id}")
//    @ResponseBody
//    public String getAlbum(@PathVariable long id){
//        return albumsDao.getOne(id).toString();
//    }
//
//    // JSON - list of posts
//    @GetMapping("/albums/json")
//    @ResponseBody
//    public List<Album> getAlbums() {
//        return albumsDao.findAllByOrderByIdDesc();
//    }
//
//    // The rest are Thymeleaf pages
//
//    @GetMapping("/albums")
//    public String getAlbums(Model model) {
////        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        System.out.println(loggedInUser.getUsername()); // print logged in user
//        model.addAttribute("albums", albumsDao.findAllByOrderByIdDesc());
//        return "albums/albums";
//    }
//
//    @GetMapping("albums/{id}")
//    public String getOneAlbum(@PathVariable long id, Model model){
//        model.addAttribute("oneAlbum", albumsDao.getOne(id));
//        return "albums/albums";
//    }
//
//    @GetMapping("/albums/{id}/edit")
//    public String editAlbumById(@PathVariable long id, Model model) {
//        model.addAttribute("album", albumsDao.getOne(id));
//        return "albums/edit";
//    }
//
//    @PostMapping("/albums/edit")
//    public String editAlbum(@RequestParam(name="id") long id,
//                           @RequestParam(name="title") String title,
//                           @RequestParam(name="description") String description, Model model) {
//        // TODO: validate logged in user can edit the post
//        Album album = albumsDao.getOne(id);
//        album.setTitle(title);
//        album.setDescription(description);
//        albumsDao.save(album);
//        return "redirect:/albums/album";
//    }
//
//    @GetMapping("/albums/{id}/delete")
//    public String deletePostById(@PathVariable long id, Model model) {
//        model.addAttribute("post", albumsDao.getOne(id));
//        return "albums/delete";
//    }
//
//    @PostMapping("/albums/delete")
//    public String deleteAlbum(@RequestParam(name="id") long id) {
//        // TODO: validate logged in user can delete the post
//        albumsDao.deleteById(id);
//        return "redirect:/albums/view";
//    }
//
//    @GetMapping("/albums/create")
//    public String createAlbumForm(Model model){
//        model.addAttribute("album", new Album());
//        return "albums/create";
//    }
//
//    @PostMapping("/albums/create")
//    public String createAlbum(@ModelAttribute Album album) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = usersDao.getOne(loggedInUser.getId());
//        album.setOwner(user);
//        albumsDao.save(album);
//        return "redirect:/albums/album";
//    }
//}
@Controller
public class AlbumController {
    private final AlbumRepository albumsDao;
    private final UserRepository usersDao;
    private final ImageRepository imagesDao;

    public AlbumController(AlbumRepository albumsDao, UserRepository usersDao, ImageRepository imagesDao) {
        this.albumsDao = albumsDao;
        this.usersDao = usersDao;
        this.imagesDao = imagesDao;
    }

    @GetMapping("/albums")
    public String index(Model model) {
        boolean isOwner = true;
        List<Album> albums = albumsDao.findAll();
        List<Album> ownerAlbums = new ArrayList<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User newUser = usersDao.getOne(user.getId());
        List<Image> images = new ArrayList<>();
        Image image = new Image();
        image.setFilename("https://cdn.filestackcontent.com/ixlBgvOrR7OiTMVdrX4F");
        image.setFileType("image/jpeg");
        images.add(image);
//        Album newAlbum = albumsDao.getOne(5L);
//        if (newUser.getAlbums().isEmpty()) {
//            usersDao.getOne(newUser.getId()).getAlbums().add(newAlbum);
//            albumsDao.getOne(newAlbum.getId()).getUsers().add(newUser);
//            usersDao.save(newUser);
//            albumsDao.save(newAlbum);
//
//        }
        if (!newUser.getOwnerAlbums().isEmpty()) {
            for (Album x : newUser.getOwnerAlbums()) {
                if (x.getImages().isEmpty()) {
                    x.setImages(images);
                }
            }
            model.addAttribute("isOwner", isOwner);
            model.addAttribute("albums", newUser.getOwnerAlbums());
        } else {
            for (Album x : newUser.getAlbums()) {
                if (x.getImages().isEmpty()) {
                    x.setImages(images);
                }
            }
            isOwner = false;
            model.addAttribute("isOwner", isOwner);
            model.addAttribute("albums", newUser.getAlbums());
        }
        return "albums/albums";
    }

    @GetMapping("/albums/{id}")
    public String show(@PathVariable long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User thisUser = usersDao.getOne(user.getId());
        Album grabbedAlbum = albumsDao.getOne(id);
        boolean isOwner = false;
        List<Image> images = new ArrayList<>();
        Image image = new Image();
        image.setFilename("https://cdn.filestackcontent.com/ixlBgvOrR7OiTMVdrX4F");
        image.setFileType("image/jpeg");
        images.add(image);
        for (Item x : grabbedAlbum.getItems()) {
            if (x.getImages().isEmpty()) {
                x.setImages(images);
            }
        }
        if (grabbedAlbum.getOwner().getId() == thisUser.getId()) {
            isOwner = true;
            model.addAttribute("isOwner", isOwner);
        }
        model.addAttribute("album", grabbedAlbum);
        model.addAttribute("items", grabbedAlbum.getItems());
        return "albums/show";
//        TODO: RETURN IS SUBJECT TO CHANGE
    }

    //    RETURNS CREATE FORM
//    TODO: MAKE CREATE ALBUM INDEX
    @GetMapping("/albums/create")
    public String showAlbumForm(Model model) {
        model.addAttribute("album", new Album());
        return "albums/create";
    }

    //    POSTING NEWLY CREATED FORM
    @PostMapping("/albums/create")
    public String createAlbum(@RequestParam(name = "title") String title, @RequestParam(name = "description") String description, @RequestParam(name = "lineage") String lineage, @RequestParam(name = "images") String[] images, @RequestParam(name = "file-type") String[] fileType) {
        User tempUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.getOne(tempUser.getId());
        Album album = new Album();
        album.setTitle(title);
        album.setDescription(description);
        album.setLineage(lineage);
        List<String> newImages = Arrays.asList(images);
        List<String> fileTypes = Arrays.asList(fileType);
        LocalDateTime currentDate = LocalDateTime.now();
//        FORMATTING TIME
        DateTimeFormatter currentDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
//        CONVERTING IT TO A STRING
        String dateString = currentDate.format(currentDateFormat);
//        User user = usersDao.getOne(1L);
        album.setOwner(user);
        album.setDeadline(dateString);
        albumsDao.save(album);
        for (int i = 0; i < newImages.size(); i++) {
            Image newImage = new Image();
            newImage.setAlbumWithImages(album);
            newImage.setFilename(newImages.get(i));
            newImage.setFileType(fileTypes.get(i));
            imagesDao.save(newImage);
        }
        return "redirect:/albums/";
    }

    //    TODO: CREATE EDIT INDEX
    @GetMapping("albums/{id}/edit")
    public String editAlbum(@PathVariable long id, Model model) {
        Album album = albumsDao.getOne(id);
        List<Image> images = album.getImages();
        List<Image> photos = new ArrayList<>();
        List<Image> videos = new ArrayList<>();
        for (Image x : images) {
            if (x.getFileType().equalsIgnoreCase("video/mp4")) {
                videos.add(x);
            } else if (x.getFileType().equalsIgnoreCase("image/jpeg")) {
                photos.add(x);
            }
        }
        model.addAttribute("album", album);
        model.addAttribute("photos", photos);
        model.addAttribute("videos", videos);
        return "albums/edit";
    }

    @PostMapping("/albums/{id}/edit")
    public String editAlbums(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "description") String description, @RequestParam(name = "lineage") String lineage, @RequestParam(name = "images") String[] images, @RequestParam(name = "file-type") String[] fileType) {
        User user = usersDao.getOne(1L);
        Album album = albumsDao.getOne(id);
        album.setTitle(title);
        album.setDescription(description);
        album.setLineage(lineage);
        album.setOwner(user);
        albumsDao.save(album);
        List<String> newImages = Arrays.asList(images);
        List<String> fileTypes = Arrays.asList(fileType);
        for (int i = 0; i < newImages.size(); i++) {
            Image newImage = new Image();
            newImage.setAlbumWithImages(album);
            newImage.setFilename(newImages.get(i));
            newImage.setFileType(fileTypes.get(i));
            imagesDao.save(newImage);
        }
        return "redirect:/albums";
    }

    @GetMapping("/albums/{id}/guest")
    public String guestPage(@PathVariable long id, Model model) {
        boolean isVisible = false;
        model.addAttribute("isVisible", isVisible);
        model.addAttribute("album", albumsDao.getOne(id));
        return "albums/add-guest";
    }

    @GetMapping("/albums/{id}/search/guest")
    public String searchGuest(@PathVariable long id, Model model, @RequestParam(name = "email") String email, @RequestParam(name = "firstname") String firstName, @RequestParam(name = "lastname") String lastName) {
        Album album = albumsDao.getOne(id);
        model.addAttribute("album", album);
        model.addAttribute("isVisible", true);
        model.addAttribute("isInvalid", false);
        User user = new User();
        String userNotFound = "Sorry, cannot find user with those credentials";
        boolean isRegistered = false;

        if (usersDao.findByEmail(email) != null) {
            user = usersDao.findByEmail(email);
            isRegistered = true;
            model.addAttribute("user", user);
            model.addAttribute("isRegistered", isRegistered);
        } else if (usersDao.findByFirstName(firstName) != null && usersDao.findByLastName(lastName) != null) {
            user = usersDao.findByFirstName(firstName);
            isRegistered = true;
            model.addAttribute("user", user);
            model.addAttribute("isRegistered", isRegistered);
        } else {
            isRegistered = false;
            model.addAttribute("userNotFound", userNotFound);
            model.addAttribute("isRegistered", isRegistered);
        }
        return "albums/add-guest";
    }

    @PostMapping("/albums/{albumId}/guest/{guestId}")
    public String addGuestToAlbum(@PathVariable long albumId, @PathVariable long guestId, Model model) {
        Album album = albumsDao.getOne(albumId);
        User user = usersDao.getOne(guestId);
        for (User x : album.getUsers()) {
            if (x.getId() == guestId) {
                model.addAttribute("album", album);
                model.addAttribute("isVisible", false);
                model.addAttribute("isInvalid", true);
                return "albums/add-guest";
            }
        }
        user.getAlbums().add(album);
        album.getUsers().add(user);
        albumsDao.save(album);
        usersDao.save(user);
        return "redirect:/albums/" + albumId;
    }

    @PostMapping("/albums/{id}/delete")
    public String deleteAlbum(@PathVariable long id) {
        albumsDao.delete(albumsDao.getOne(id));
        return "redirect:/albums";
    }

    @PostMapping("/albums/{albumId}/image/{imageId}")
    public String deleteAlbumImage(@PathVariable long albumId, @PathVariable long imageId) {
        Image imageDelete = imagesDao.getOne(imageId);
        imagesDao.delete(imageDelete);
        return "redirect:/albums/" + albumId + "/edit";

    }
}
