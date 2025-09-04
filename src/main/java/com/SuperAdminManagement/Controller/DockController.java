package com.SuperAdminManagement.Controller;

import com.SuperAdminManagement.Model.*;
import com.SuperAdminManagement.Request.AcceptedRejectedContainerRequest;
import com.SuperAdminManagement.Request.DockTimeActivityRequest;
import com.SuperAdminManagement.Response.*;
import com.SuperAdminManagement.Service.DockingService;
import com.SuperAdminManagement.Utils.ConstantsForAPIs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ConstantsForAPIs.PREFIX)
public class DockController {

    @Autowired
    private DockingService dockingService;

    @GetMapping(ConstantsForAPIs.GET_ALL_DOCK_BY_DOCK_SUPERVISOR)
    public BaseResponse<Dock> getAllDock(){
        return dockingService.getDock();
    }

    @GetMapping(ConstantsForAPIs.GET_BY_CINNUMBER)
    public BaseResponse<DockResponse> getDetailByCinNumber(@RequestParam String cinNumber,@RequestParam Integer dockId){
        BaseResponse<DockResponse> baseResponse = dockingService.getByCINNumber(cinNumber,dockId);
        return baseResponse;
    }

    @PostMapping(ConstantsForAPIs.SAVE_ACCEPTED_REJECTED_CONTAINER)
    public BaseResponse<AcceptedRejectedContainer> saveAcceptedAndRejectedContainer(@RequestBody List<AcceptedRejectedContainerRequest>acceptedRejectedContainerRequests){
        return dockingService.saveAcceptedAndRejectedContainer(acceptedRejectedContainerRequests);
    }
    @GetMapping(ConstantsForAPIs.GENERATE_CRR_NUMBER)
    public BaseResponse<Dock> generateCRRNumber(@RequestParam String cinNumber,@RequestParam String dockId){

        return dockingService.generateCRRNumber(cinNumber,dockId);
    }

    @GetMapping(ConstantsForAPIs.GET_ALL_DOCKS_BY_CRR_GENERATED)
    public BaseResponse<DockByCRResponse> getAllDockByCRRGenerated(){
        return dockingService.getAllDockByCRRGenerated();
    }
    @GetMapping(ConstantsForAPIs.GET_PICK_ACCEPTED_AND_REJECTED_CONTAINER)
    public BaseResponse<AcceptedRejectedContainerBarcode> getPickAcceptedAndRejectedContainer(@RequestParam Integer itemId,
                                                                                              @RequestParam(required = false) Integer asnLineId,
                                                                                              @RequestParam(required = false) Integer poLine){
        return dockingService.getPickAcceptedAndRejectedContainer(itemId,asnLineId,poLine);
    }
    @GetMapping(ConstantsForAPIs.GET_PUT_ACCEPTED_AND_REJECTED_CONTAINER)
    public BaseResponse<AcceptedRejectedContainerBarcode> getPutAcceptedAndRejectedContainer(@RequestParam Integer itemId,
                                                                                             @RequestParam(required = false) Integer asnLineId,
                                                                                             @RequestParam(required = false) Integer poLine){
        return dockingService.getPutAcceptedAndRejectedContainer(itemId,asnLineId,poLine);
    }

    @PostMapping(ConstantsForAPIs.SAVE_PICK_ACCEPTED_AND_REJECTED_CONTAINER)
    public BaseResponse<AcceptedRejectedContainerBarcode> savePickAcceptedAndRejectedContainer(@RequestParam Integer itemId,
                                                                                               @RequestParam(required = false) Integer asnLineId,
                                                                                               @RequestParam(required = false) Integer poLine){
        return dockingService.savePickAcceptedAndRejectedContainer(itemId,asnLineId,poLine);
    }
    @PostMapping(ConstantsForAPIs.SAVE_PUT_ACCEPTED_AND_REJECTED_CONTAINER)
    public BaseResponse<AcceptedRejectedContainerBarcode> savePutAcceptedAndRejectedContainer(@RequestParam Integer itemId,
                                                                                              @RequestParam(required = false) Integer asnLineId,
                                                                                              @RequestParam(required = false) Integer poLine,
                                                                                              @RequestParam Integer stagingAreaId,
                                                                                              @RequestParam Boolean status){
        return dockingService.savePutAcceptedAndRejectedContainer(itemId,asnLineId,poLine,stagingAreaId,status);
    }

    @PostMapping(ConstantsForAPIs.SAVE_DOCK_ACTIVITY)
    public BaseResponse<DockTimeActivity> saveDockActivity(@RequestBody List<DockTimeActivityRequest> dockTimeActivityRequests){
        return dockingService.saveDockActivity(dockTimeActivityRequests);
    }

    @GetMapping(ConstantsForAPIs.GET_DOCK_OPERATOR)
    public BaseResponse<Users> getDockOperator(@PathVariable Integer dockId){
        return dockingService.getDockOperator(dockId);
    }

    @GetMapping(ConstantsForAPIs.GET_STAGING_AREA_BY_DOCK)
    public BaseResponse<AcceptedRejectedStagingArea> getStagingAreaByDock(@PathVariable Integer dockId){
        return dockingService.getStagingAreaByDock(dockId);
    }

    @PostMapping(ConstantsForAPIs.SET_DOCK_OPERATOR_ON_DOCK)
    public BaseResponse<AcceptedRejectedContainer> setDockOperatorOnDock(@RequestParam Integer dockId,
                                                                          @RequestParam Integer dockOperatorId){
        return dockingService.setDockOperatorOnDock(dockId,dockOperatorId);
    }

    @GetMapping(ConstantsForAPIs.GET_ALL_ITEM_STAGING_PUT)
    public BaseResponse<ItemDto> getAllItemCustomResponse(){
        return dockingService.getAllItemCustomResponse();
    }
    @GetMapping(ConstantsForAPIs.GET_ALL_CRR_BARCODE)
    public BaseResponse<CRRResponse> getCRRBarcode(@RequestParam Boolean isAccepted,
                                                   @RequestParam(required = false) Integer asnLineId,
                                                   @RequestParam(required = false) Integer poLineId){
        return dockingService.getCRRBarcode(isAccepted,asnLineId,poLineId);
    }

}
