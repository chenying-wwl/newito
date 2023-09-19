package com.qf.user.manager.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/area")
@Tag(name = "AdminController", description = "管理员管理")
public class AdminController {

}
