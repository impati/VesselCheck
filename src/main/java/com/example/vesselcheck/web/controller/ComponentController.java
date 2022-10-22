package com.example.vesselcheck.web.controller;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.*;
import com.example.vesselcheck.domain.service.Dto.ComponentForm;
import com.example.vesselcheck.domain.service.Dto.ComponentInfo;
import com.example.vesselcheck.domain.service.Dto.ComponentSearchCond;
import com.example.vesselcheck.domain.service.ComponentService;
import com.example.vesselcheck.domain.service.FileStore;
import com.example.vesselcheck.domain.service.VesselService;
import com.example.vesselcheck.web.config.SessionConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ComponentController {

    private final VesselService vesselService;
    private final ClientRepository clientRepository;
    private final ComponentService componentService;
    private final FileStore fileStore;
    /**
     * 블럭 등록
     */
    @GetMapping("/block/register/{vesselId}")
    public String blockRegisterPage(@PathVariable Long vesselId,
                                    @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId, Model model){

        if(!isInspector(clientId))return "Not Allow";

        model.addAttribute("vesselInfo",vesselService.vesselInfo(vesselId));
        model.addAttribute("blockForm",new BlockForm());
        model.addAttribute("workingSteps",WorkingStep.values());
        return "component/blockRegister";
    }

    @PostMapping("/block/register/{vesselId}")
    public String blockRegister(@PathVariable Long vesselId,
                                @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId,
                                @ModelAttribute BlockForm blockForm){
        if(!isInspector(clientId))return "Not Allow";


        componentService.registerBlock(vesselId,clientId,blockForm.getBlockName(),blockForm.getWorkingStep());
        return "redirect:/vessel/"+ vesselId;
    }


    /**
     * 부품 조회
     */
    @GetMapping("/component/components/{vesselId}")
    public String componentSearch(@PathVariable Long vesselId,
                                  @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId,
                                  @ModelAttribute ComponentSearchCond componentSearchCond,
                                  Model model){

        if(!isInspector(clientId)) return "Not Allow";


        componentSearchCond.setClientId(clientId);
        componentSearchCond.setVesselId(vesselId);

        model.addAttribute("workingStatusList", WorkingStatus.values());
        model.addAttribute("FaultTypeList", FaultType.values());
        model.addAttribute("vesselInfo",vesselService.vesselInfo(vesselId));
        model.addAttribute("components",componentService.searchComponent(componentSearchCond)
                .stream()
                .map(c ->new ComponentInfo(c.getId(),c.getFaultType(),
                        c.getComponentName(),c.getSequenceNumber(),c.getWorkingStatus()))
                .collect(Collectors.toList()));

        return "component/components";
    }



    /**
     * 부품 업로드
     *
     */
    @GetMapping("/component/register/{vesselId}")
    public String componentRegisterPage(@PathVariable Long vesselId ,
                                        @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId,
                                        Model model ) {


        if(!isInspector(clientId))return "Not Allow";

        model.addAttribute("vesselInfo",vesselService.vesselInfo(vesselId));
        model.addAttribute("componentForm",new ComponentForm());
        return "component/upload";
    }

    @PostMapping("/component/register/{vesselId}")
    public String componentRegister(@PathVariable Long vesselId ,
                                    @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId,
                                    @ModelAttribute ComponentForm componentForm) {
        if(!isInspector(clientId))return "Not Allow";
        componentService.registerComponentList(componentForm);
        return "redirect:/component/components/" + vesselId;
    }


    /**
     *  단순히 부품 페이지
     */
    @GetMapping("/component/{componentId}")
    public String componentPage(@PathVariable Long componentId,
                                @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId,
                                Model model){
        if(!isInspector(clientId))return "Not Allow";
        model.addAttribute("componentInfo",componentService.componentInfo(componentId));
        return "component/componentPage";
    }


    /**
     * 이미지 응답
     */
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }


    /**
     * 최신 버전
     * 이미지 응답.
     */
    @ResponseBody
    @GetMapping(value = "/image/{filename}")
    public ResponseEntity<Resource> userSearch(@PathVariable String filename) throws IOException {
        String inputFile = fileStore.getFullPath(filename);
        Path path = new File(inputFile).toPath();
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .body(resource);
    }



    private Boolean isInspector(Long clientId){
        Client client = clientRepository.findById(clientId).orElse(null);
        if(client instanceof Manufacturer) return false;
        else return true;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class BlockForm{
        private String blockName;
        private WorkingStep workingStep;
    }







}
