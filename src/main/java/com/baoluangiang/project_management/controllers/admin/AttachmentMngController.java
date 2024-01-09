package com.baoluangiang.project_management.controllers.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/admin/attachmentManagement")
public class AttachmentMngController {

}
