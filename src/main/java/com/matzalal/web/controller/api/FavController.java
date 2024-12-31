package com.matzalal.web.controller.api;

import com.matzalal.web.entity.FavView;
import com.matzalal.web.service.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiFavController")
@RequestMapping("/api/favs")
public class FavController {

    @Autowired
    private FavService service;

    @DeleteMapping("{ids}")
    public List<FavView> delete(
            @PathVariable Long[] ids
    ){
        Long restId = ids[0];
        Long userId = ids[1];

        boolean result = service.delete(restId, userId);
        System.out.println("지우기 결과 : " + result);

        List<FavView> list = service.getViewListByUserId(userId);
        System.out.println("지운 후의 fav리스트 :::::" + list);

        return list;
    }


    @GetMapping()
    public List<FavView> list(
            @RequestParam("q") Long id
    ){
        List<FavView> list = service.getViewListByRestId(id);
        System.out.println(list);

        return list;
    }

}
