package sharon.bucketlist.global.oauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import sharon.bucketlist.global.oauth.service.Oauth2Service;

@RestController
public class Oauth2Controller {

    private final Oauth2Service oauth2Service;

    public Oauth2Controller(Oauth2Service kakaoApiService) {
        this.oauth2Service = kakaoApiService;
    }

    @GetMapping("/authorize")
    public RedirectView authorize(@RequestParam(required = false) String scope) {
        return new RedirectView(oauth2Service.getAuthUrl(scope));
    }

    @GetMapping("/redirect")
    public RedirectView handleRedirect(@RequestParam String code) {
        boolean isSuccess = oauth2Service.handleAuthorizationCallback(code);
        return new RedirectView("/index.html?login=" + (isSuccess ? "success" : "error"));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return oauth2Service.getUserProfile();
    }

    @GetMapping("/friends")
    public ResponseEntity<?> getFriends() {
        return oauth2Service.getFriends();
    }

    @GetMapping("/message")
    public ResponseEntity<?> sendMessage() {
        return oauth2Service.sendMessage(oauth2Service.createDefaultMessage());
    }

    @GetMapping("/friend-message")
    public ResponseEntity<?> sendMessageToFriend(@RequestParam String uuid) {
        return oauth2Service.sendMessageToFriend(uuid, oauth2Service.createDefaultMessage());
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return oauth2Service.logout();
    }

    @GetMapping("/unlink")
    public ResponseEntity<?> unlink() {
        return oauth2Service.unlink();
    }
}