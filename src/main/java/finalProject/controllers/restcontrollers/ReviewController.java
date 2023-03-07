package finalProject.controllers.restcontrollers;

import finalProject.model.Parameter;
import finalProject.model.Review;
import finalProject.services.ParameterService;
import finalProject.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping(value = "/{id}")
    public List<Review> getAllReviewByItem_id(@PathVariable (name = "id") Long item_id) {
        return reviewService.getReviewsByItem_id(item_id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Review addReview(
            @RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Review updateReview(
            @RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Boolean deleteReview(@RequestBody Review review) {
        return reviewService.deleteReview(review);
    }
}
