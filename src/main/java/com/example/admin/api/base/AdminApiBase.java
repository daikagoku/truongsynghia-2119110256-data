package com.example.admin.api.base;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.api.base.ApiBase;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminApiBase extends ApiBase{
}
