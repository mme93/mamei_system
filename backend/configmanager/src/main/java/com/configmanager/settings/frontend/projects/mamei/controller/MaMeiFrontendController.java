package com.configmanager.settings.frontend.projects.mamei.controller;

import com.configmanager.settings.frontend.projects.mamei.model.EMameiSpecific;
import com.configmanager.settings.frontend.projects.mamei.service.MaMeiFrontendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/frontend/mamei")
public class MaMeiFrontendController {

    private final MaMeiFrontendService maMeiFrontendService;

    public MaMeiFrontendController(MaMeiFrontendService maMeiFrontendService) {
        this.maMeiFrontendService = maMeiFrontendService;
    }

    @GetMapping("")
    public ResponseEntity<List<Object>> loadFrontendConfigs(@RequestHeader(value = "X-Username", required = false) String username) {
        return new ResponseEntity<>(maMeiFrontendService.loadFrontendConfigs(username), HttpStatus.OK);
    }

    @GetMapping("/{specification}")
    public ResponseEntity<Object> loadSpecificFrontendConfig(@RequestHeader(value = "X-Username", required = false) String username,
                                                             @PathVariable String specification) {
        Optional<Object> configOpt = maMeiFrontendService.loadSpecificFrontendConfig(username, EMameiSpecific.valueOf(specification));
        if (configOpt.isPresent()) {
            Object obj=configOpt.get();
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{specification}")
    public ResponseEntity<Object> createSpecificFrontendConfig(@RequestHeader(value = "X-Username", required = false) String username,
                                                               @PathVariable String specification, @RequestBody Object config) {
        return new ResponseEntity<>(maMeiFrontendService.
                createSpecificFrontendConfig(username, EMameiSpecific.valueOf(specification), config), HttpStatus.CREATED);
    }

    @PutMapping("/{specification}")
    public ResponseEntity<Object> updateSpecificFrontendConfig(@RequestHeader(value = "X-Username", required = false) String username,
                                                               @PathVariable String specification, @RequestBody Object config) {
        return new ResponseEntity<>(maMeiFrontendService.
                updateSpecificFrontendConfig(EMameiSpecific.valueOf(specification), config), HttpStatus.CREATED);
    }

    @DeleteMapping("/{specification}")
    public ResponseEntity<Object> deleteSpecificFrontendConfig(@RequestHeader(value = "X-Username", required = false) String username,
                                                               @PathVariable String specification) {
        maMeiFrontendService.deleteSpecificFrontendConfig(EMameiSpecific.valueOf(specification), username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
