package com.example.demo.web.rest;

import com.example.demo.security.AuthoritiesConstants;
import com.example.demo.service.BlogService;
import com.example.demo.service.dto.BlogDTO;
import com.example.demo.web.rest.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogResource {
    private BlogService blogService;

    public BlogResource(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<BlogDTO>> getAllBlogs(Pageable pageable) {
        final Page<BlogDTO> page = blogService.getAllBlogs(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/blogs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
