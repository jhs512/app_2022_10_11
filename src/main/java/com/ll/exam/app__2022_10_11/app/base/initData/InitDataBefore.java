package com.ll.exam.app__2022_10_11.app.base.initData;

import com.ll.exam.app__2022_10_11.app.cart.entity.CartItem;
import com.ll.exam.app__2022_10_11.app.cart.service.CartService;
import com.ll.exam.app__2022_10_11.app.member.entity.Member;
import com.ll.exam.app__2022_10_11.app.member.service.MemberService;
import com.ll.exam.app__2022_10_11.app.song.entity.Song;
import com.ll.exam.app__2022_10_11.app.song.service.SongService;
import com.ll.exam.app__2022_10_11.product.entity.Product;
import com.ll.exam.app__2022_10_11.product.service.ProductService;

public interface InitDataBefore {
    default void before(MemberService memberService, SongService songService, ProductService productService, CartService cartService) {
        Member member1 = memberService.join("user1", "1234", "user1@test.com");
        Member member2 = memberService.join("user2", "1234", "user2@test.com");

        Song song1 = songService.create(member1, "노래 1", "내용 1");
        Song song2 = songService.create(member1, "노래 2", "내용 2");
        Song song3 = songService.create(member2, "노래 3", "내용 3");
        Song song4 = songService.create(member2, "노래 4", "내용 4");
        Song song5 = songService.create(member1, "노래 5", "내용 5");
        Song song6 = songService.create(member1, "노래 6", "내용 6");
        Song song7 = songService.create(member2, "노래 7", "내용 7");
        Song song8 = songService.create(member2, "노래 8", "내용 8");

        Product product1 = productService.create(song1, "그리움", 1_900);
        Product product2 = productService.create(song3, "미련", 2_900);
        Product product3 = productService.create(song5, "슬픔", 3_900);
        Product product4 = productService.create(song7, "바다", 4_900);
        Product product5 = productService.create(song8, "안녕", 5_900);

        CartItem cartItem1 = cartService.addItem(member1, product1);
        CartItem cartItem2 = cartService.addItem(member1, product2);
        CartItem cartItem3 = cartService.addItem(member2, product3);
        CartItem cartItem4 = cartService.addItem(member2, product4);

        memberService.addCash(member1, 10_000, "충전__무통장입금");
        memberService.addCash(member1, 20_000, "충전__무통장입금");
        memberService.addCash(member1, -5_000, "출금__일반");
        memberService.addCash(member1, 1_000_000, "충전__무통장입금");

        memberService.addCash(member2, 2_000_000, "충전__무통장입금");
    }
}
