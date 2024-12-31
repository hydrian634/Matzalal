package com.matzalal.web.controller.api;

import com.matzalal.web.entity.Celeb;
import com.matzalal.web.entity.CelebRestView;
import com.matzalal.web.entity.Comment;
import com.matzalal.web.entity.Review;
import com.matzalal.web.service.CelebListService;
import com.matzalal.web.service.CelebRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiCelebController")
@RequestMapping("/api/celebs")
public class CelebRestController {

    @Autowired
    private CelebRestService celebRestService;
    @Autowired
    private CelebListService celebListService;

    @GetMapping
    public List<CelebRestView> list(@RequestParam(name="c", required = false) Long celebId
    ){
        List<CelebRestView> celebRestList = celebRestService.getCelebListViewById(celebId);
        List<Celeb> celebCategoryList = celebListService.getCelebList();

        System.out.println(celebId);
        System.out.println(celebRestList);
        return celebRestList;
    }
//    @GetMapping("{id}")
//    public Menu detail(@PathVariable long id
////        , @PathVariable String name
//    ){
//        Menu menu = service.getById(id);
//        return menu;
//    }
//@GetMapping
//public List<Celeb> list(@RequestParam(name="restId", required = false) Long celebId
//){
////    List<CelebRestView> celebRestList = celebRestService.getCelebListViewById(celebId);
//    List<Celeb> celebCategoryList = celebListService.getCelebList(celebId);
//
//    System.out.println(celebId);
//    System.out.println(celebCategoryList);
//    return celebCategoryList;
//}

}
