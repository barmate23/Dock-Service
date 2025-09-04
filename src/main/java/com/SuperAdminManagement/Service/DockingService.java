package com.SuperAdminManagement.Service;

import com.SuperAdminManagement.Model.*;
import com.SuperAdminManagement.Request.AcceptedRejectedContainerRequest;
import com.SuperAdminManagement.Request.DockTimeActivityRequest;
import com.SuperAdminManagement.Response.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DockingService {

    BaseResponse<Dock> getDock();

    BaseResponse<DockTimeActivity> saveDockActivity(List<DockTimeActivityRequest> dockTimeActivityRequests);

    BaseResponse<DockResponse> getByCINNumber(String cinNumber, Integer dockId);

    BaseResponse<AcceptedRejectedContainer> saveAcceptedAndRejectedContainer(List<AcceptedRejectedContainerRequest> acceptedRejectedContainerRequests);

    BaseResponse generateCRRNumber(String cinNumber,String dockId);

    BaseResponse<DockByCRResponse>getAllDockByCRRGenerated();

    BaseResponse<AcceptedRejectedContainerBarcode> getPickAcceptedAndRejectedContainer(Integer itemId,Integer asnLineId, Integer poLineId);

    BaseResponse<AcceptedRejectedContainerBarcode> savePickAcceptedAndRejectedContainer(Integer itemId, Integer asnLineId, Integer poLine);

    BaseResponse<AcceptedRejectedContainerBarcode> getPutAcceptedAndRejectedContainer(Integer itemId, Integer asnLineId, Integer poLine);

    BaseResponse<AcceptedRejectedContainerBarcode> savePutAcceptedAndRejectedContainer(Integer itemId, Integer asnLineId, Integer poLineId, Integer stagingAreaId,Boolean status);

    BaseResponse<AcceptedRejectedStagingArea> getStagingAreaByDock(Integer dockId);

    BaseResponse<Users> getDockOperator(Integer dockId);

    BaseResponse<AcceptedRejectedContainer> setDockOperatorOnDock(Integer dockId, Integer dockOperatorId);

    BaseResponse<ItemDto> getAllItemCustomResponse();

    BaseResponse<CRRResponse> getCRRBarcode(Boolean isAccepted, Integer asnLineId, Integer poLineId);
}
