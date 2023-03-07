package finalProject.services;

import finalProject.model.CategoryOfParameter;
import finalProject.model.Item;
import finalProject.model.Parameter;
import finalProject.model.Review;
import finalProject.repositories.ItemRepository;
import finalProject.repositories.ParameterRepository;
import finalProject.repositories.ReviewRepository;
import finalProject.repositories.SecondCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserService userService;

    public List<Review> getReviewsByUser_id(Long user_id){
        return reviewRepository.findAllByUser_IdOrderByLocalDateTimeDesc(user_id);
    }

    public List<Review> getReviewsByItem_id(Long item_id){
        return reviewRepository.findAllByItem_IdOrderByLocalDateTimeDesc(item_id);
    }

    public Review addReview(Review review){
        review.setUser(userService.getUser());
        review.setLocalDateTime(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public boolean deleteReview(Review review){
        boolean bol = true;
        try{
            reviewRepository.delete(review);
        } catch (Exception e){
            bol = false;
        }
        return bol;
    }
}
