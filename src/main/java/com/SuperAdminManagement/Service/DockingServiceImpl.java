package com.SuperAdminManagement.Service;

import com.SuperAdminManagement.Model.*;
import com.SuperAdminManagement.Repository.*;
import com.SuperAdminManagement.Request.AcceptedRejectedContainerRequest;
import com.SuperAdminManagement.Request.DockTimeActivityRequest;
import com.SuperAdminManagement.Response.*;
import com.SuperAdminManagement.Utils.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class DockingServiceImpl implements DockingService {

    @Autowired
    LoginUser loginUser;

    @Autowired
    private DockRepository dockRepository;

    @Autowired
    private ASNHeadRepository asnHeadRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ASNOrderLineRepository asnOrderLineRepository;

    @Autowired
    private CodeMapperRepository codeMapperRepository;

    @Autowired
    private PurchaseOrderHeadRepository purchaseOrderHeadRepository;

    @Autowired
    private PurchaseOrderLineRepository purchaseOrderLineRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private AcceptedRejectedContainerRepository acceptedRejectedContainerRepository;
    @Autowired
    private
    AcceptedRejectedContainerBarcodeRepository acceptedRejectedContainerBarcodeRepository;
    @Autowired
    CommonMasterRepository commonMasterRepository;

    @Autowired
    StageAreaRepository stageAreaRepository;

    @Autowired
    AcceptedRejectedStagingAreaRepository acceptedRejectedStagingAreaRepository;

    @Autowired
    DockTimeActivityRepository dockTimeActivityRepository;

    @Override
    public BaseResponse<Dock> getDock() {
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockService - getDock - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  GET DOCK METHOD START");
        BaseResponse<Dock> baseResponse= new BaseResponse<>();
        List<Dock> docks;
        try {
            docks = dockRepository.findByIsDeletedAndSubOrganizationIdAndDockSupervisorId(false, loginUser.getSubOrgId(),loginUser.getUserId());
            baseResponse.setCode(Const.CODE_1);
            baseResponse.setStatus(Const.STATUS_200);
            baseResponse.setData(docks);
            baseResponse.setLogId(loginUser.getLogId());
            baseResponse.setMessage(Const.DOCKSFETCHSUCCESSFULLY.toUpperCase(Locale.ROOT));
        }catch(Exception e){
            baseResponse.setCode(Const.CODE_1);
            baseResponse.setStatus(Const.STATUS_500);
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            baseResponse.setMessage(Const.DOCKSFETCHFAILED);
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockService - getDock - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," ERROR OCCURS AT GETTING DOCK EXECUTED TIME  :: "+ (endTime - startTime),e);
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockService - getDock - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET DOCK METHOD EXECUTED END  EXECUTED TIME :: "+ (endTime - startTime));
        return baseResponse;
    }
    @Override
    public BaseResponse<DockTimeActivity> saveDockActivity(List<DockTimeActivityRequest> dockTimeActivityRequests) {
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockService - saveDockActivity - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  SAVE DOCK ACTIVITY METHOD START");
        BaseResponse<DockTimeActivity> baseResponse= new BaseResponse<>();
        List<DockTimeActivity> dockTimeActivities = new ArrayList<>();
        try {
            for (DockTimeActivityRequest dockTimeActivityRequest:dockTimeActivityRequests) {
                DockTimeActivity dockTimeActivity=new DockTimeActivity();
                dockTimeActivity.setDockId(dockRepository.findByIsDeletedAndSubOrganizationIdAndId(false,loginUser.getSubOrgId(),dockTimeActivityRequest.getDockId()));
                dockTimeActivity.setDockSupervisor(userRepository.findByIsDeletedAndSubOrganizationIdAndId(false,loginUser.getSubOrgId(),loginUser.getUserId()));
                dockTimeActivity.setStartTime(dockTimeActivityRequest.getStartTime());
                dockTimeActivity.setStatus(dockTimeActivityRequest.getStatus());
                dockTimeActivity.setEndTime(dockTimeActivityRequest.getEndTime());
                dockTimeActivity.setOrganizationId(loginUser.getOrgId());
                dockTimeActivity.setSubOrganizationId(loginUser.getSubOrgId());
                dockTimeActivity.setCreatedBy(loginUser.getUserId());
                dockTimeActivity.setCreatedOn(new Date());
                dockTimeActivity.setModifiedBy(loginUser.getUserId());
                dockTimeActivity.setModifiedOn(new Date());
                dockTimeActivity.setIsDeleted(false);
                dockTimeActivityRepository.save(dockTimeActivity);
                dockTimeActivities.add(dockTimeActivity);
            }
            baseResponse.setCode(Const.CODE_1);
            baseResponse.setStatus(Const.STATUS_200);
            baseResponse.setData(dockTimeActivities);
            baseResponse.setLogId(loginUser.getLogId());
            baseResponse.setMessage(" SUCCESSFULLY SAVE DOCK ACTIVITY ");
        }catch(Exception e){
            baseResponse.setCode(Const.CODE_1);
            baseResponse.setStatus(Const.STATUS_500);
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            baseResponse.setMessage(" FAILED TO SAVE DOCK ACTIVITY ");
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockService - getDock - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," ERROR OCCURS AT GETTING DOCK EXECUTED TIME  :: "+ (endTime - startTime),e);
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockService - getDock - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET DOCK METHOD EXECUTED END  EXECUTED TIME :: "+ (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<DockResponse> getByCINNumber(String cinNumber, Integer dockId) {
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockService - getbyCINNumber - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  GET BY CIN NUMBER METHOD START");
        BaseResponse<DockResponse> baseResponse= new BaseResponse<>();
        List<ItemDto>itemDtoList=new ArrayList<>();
        DockResponse dockResponse=new DockResponse();
        try {
            log.info("LogId:{} - DockService - getbyCINNumber - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"SubOrganisation Id :: " , loginUser.getSubOrgId());
            if(cinNumber !=null) {
                Dock dock=dockRepository.findByIsDeletedAndSubOrganizationIdAndId(false,loginUser.getSubOrgId(),dockId);
                dockResponse.setDockId(dock.getDockId());
                dockResponse.setDockName(dock.getDockName());
                CodeMapper codeMapper = codeMapperRepository.findByIsDeletedAndSubOrganizationIdAndCinBarcodeNumber(false, loginUser.getSubOrgId(), cinNumber);
                if(codeMapper.getAsnNumber()!=null) {
                    ASNHead asnData = asnHeadRepository.findByIsDeletedAndSubOrganizationIdAndAsnNumber(false, loginUser.getSubOrgId(), codeMapper.getAsnNumber());
                    dockResponse.setInstrument(Const.ASN);
                    dockResponse.setAsnNumber(asnData.getAsnNumber());
                    dockResponse.setPoNumber(asnData.getPurchaseOrderHead().getPurchaseOrderNumber());
                    dockResponse.setDueDate(asnData.getDeliveryDate());
                    dockResponse.setTime(asnData.getDeliveryTime());
                    Users users=userRepository.findByIsDeletedAndSubOrganizationIdAndId(false,loginUser.getSubOrgId(),asnData.getCreatedBy());
                    dockResponse.setBuyer(users.getFirstName()+" "+users.getLastName());
                    dockResponse.setBuyerMob(users.getMobileNo());
                    dockResponse.setSupplier(asnData.getPurchaseOrderHead().getSupplier().getSupplierName());
                    dockResponse.setSupplierId(asnData.getPurchaseOrderHead().getSupplier().getSupplierId());
                    dockResponse.setSupplierName(asnData.getPurchaseOrderHead().getSupplier().getContactPersonName());
                    dockResponse.setAlternativeMobile(asnData.getPurchaseOrderHead().getSupplier().getPrimaryPhone());
                    dockResponse.setLandline(asnData.getPurchaseOrderHead().getSupplier().getAreaCode()+"-"+asnData.getPurchaseOrderHead().getSupplier().getAlternatePhone());
                    dockResponse.setLocation(asnData.getPurchaseOrderHead().getSupplier().getDistrict()+","+asnData.getPurchaseOrderHead().getSupplier().getPostCode());
                    List<ASNOrderLine> asnOrderLine=asnOrderLineRepository.findByIsDeletedAndSubOrganizationIdAndAsnHeadIdId(false,loginUser.getSubOrgId(),asnData.getId());
                    for (ASNOrderLine asnLine:asnOrderLine) {
                        List<AcceptedRejectedContainer> acceptedRejectedContainers1 = acceptedRejectedContainerRepository.findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndAsnOrderLineId(false,Const.CRRGENERATED, loginUser.getSubOrgId(), asnLine.getId());
                        if (acceptedRejectedContainers1.isEmpty()) {
                            if (asnLine.getItem().getDock().getDockId().equals(dock.getDockId())) {
                                ItemDto itemDto = new ItemDto();
                                itemDto.setId(asnLine.getItem().getId());
                                itemDto.setAsnLineId(asnLine.getId());
                                itemDto.setItemCode(asnLine.getItem().getItemCode());
                                itemDto.setItemName(asnLine.getItem().getName());
                                itemDto.setContainerQty(asnLine.getNumberOfContainer());
                                itemDto.setItemQty(asnLine.getInvoiceQuantity());
                                List<AcceptedRejectedContainer> acceptedRejectedContainers = acceptedRejectedContainerRepository.findByIsDeletedAndSubOrganizationIdAndAsnOrderLineId(false, loginUser.getSubOrgId(), asnLine.getId());
                                if (acceptedRejectedContainers != null && !acceptedRejectedContainers.isEmpty()) {
                                    itemDto.setAcceptedContainerQty(acceptedRejectedContainers.stream()
                                            .filter(AcceptedRejectedContainer::getIsAccepted)
                                            .mapToInt(AcceptedRejectedContainer::getAcceptedRejectedContainerQuantity).sum());
                                    itemDto.setRejectedContainerQty(acceptedRejectedContainers.stream()
                                            .filter(container -> !container.getIsAccepted())
                                            .mapToInt(AcceptedRejectedContainer::getAcceptedRejectedContainerQuantity)
                                            .sum());
                                }
                                itemDtoList.add(itemDto);
                            }
                        }
                    }
                    dockResponse.setItems(itemDtoList);
                }
                else if(codeMapper.getPoNumber()!=null){
                    PurchaseOrderHead purchaseOrderHead=purchaseOrderHeadRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderNumber(false, loginUser.getSubOrgId(), codeMapper.getPoNumber());
                    dockResponse.setInstrument("Purchase Order");
                    dockResponse.setPoNumber(purchaseOrderHead.getPurchaseOrderNumber());
                    dockResponse.setDueDate(purchaseOrderHead.getDeliverByDate());
                    dockResponse.setSupplier(purchaseOrderHead.getSupplier().getSupplierName());
                    dockResponse.setSupplierId(purchaseOrderHead.getSupplier().getSupplierId());
                    dockResponse.setSupplierName(purchaseOrderHead.getSupplier().getContactPersonName());
                    dockResponse.setLocation(purchaseOrderHead.getSupplier().getDistrict()+","+purchaseOrderHead.getSupplier().getPostCode());
                    List<PurchaseOrderLine>purchaseOrderLines=purchaseOrderLineRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadId(false,loginUser.getSubOrgId(),purchaseOrderHead.getId());
                    for (PurchaseOrderLine purchaseOrderLine:purchaseOrderLines) {
                        List<AcceptedRejectedContainer> acceptedRejectedContainers1 = acceptedRejectedContainerRepository.findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndPurchaseOrderLineId(false, Const.CRRGENERATED, loginUser.getSubOrgId(), purchaseOrderLine.getId());
                        if (acceptedRejectedContainers1.isEmpty()) {
                            if (purchaseOrderLine.getItem().getDock().getDockId().equals(dock.getDockId())) {
                                ItemDto itemDto = new ItemDto();
                                itemDto.setId(purchaseOrderLine.getItem().getId());
                                itemDto.setPoLineId(purchaseOrderLine.getId());
                                itemDto.setItemCode(purchaseOrderLine.getItem().getItemCode());
                                itemDto.setItemName(purchaseOrderLine.getItem().getName());
                                itemDto.setContainerQty(purchaseOrderLine.getNumberOfContainer());
                                itemDto.setItemQty(containerRepository.findByIsDeletedAndSubOrganizationIdAndItemId(false, loginUser.getSubOrgId(), purchaseOrderLine.getItem().getId()).getItemQty());
                                List<AcceptedRejectedContainer> acceptedRejectedContainers = acceptedRejectedContainerRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderLineId(false, loginUser.getSubOrgId(), purchaseOrderLine.getId());
                                if (acceptedRejectedContainers != null && !acceptedRejectedContainers.isEmpty()) {
                                    itemDto.setAcceptedContainerQty(acceptedRejectedContainers.stream()
                                            .filter(AcceptedRejectedContainer::getIsAccepted)
                                            .mapToInt(AcceptedRejectedContainer::getAcceptedRejectedContainerQuantity).sum());
                                    itemDto.setRejectedContainerQty(acceptedRejectedContainers.stream()
                                            .filter(container -> !container.getIsAccepted())
                                            .mapToInt(AcceptedRejectedContainer::getAcceptedRejectedContainerQuantity)
                                            .sum());
                                }
                                itemDtoList.add(itemDto);
                            }
                        }
                    }
                    dockResponse.setItems(itemDtoList);
                }
                baseResponse.setCode(Const.CODE_1);
                baseResponse.setStatus(Const.STATUS_200);
                baseResponse.setData(Arrays.asList(dockResponse));
                baseResponse.setLogId(loginUser.getLogId());
                baseResponse.setMessage(Const.ASNLINEFETCHSUCCESSFULLY);
            }
            else{
                baseResponse.setCode(Const.CODE_0);
                baseResponse.setStatus(Const.STATUS_500);
                baseResponse.setData(null);
                baseResponse.setMessage("CIN number is null..!");
            }
        }catch (Exception e){
            baseResponse.setCode(Const.CODE_0);
            baseResponse.setStatus(Const.STATUS_500);
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            baseResponse.setMessage("ERROR OCCURS AT GETTING CIN BY NUMBER");
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockService - getbyCINNumber - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," ERROR OCCURS AT GETTING CIN BY NUMBER EXECUTED TIME  :: "+ (endTime - startTime),e);
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockService - getbyCINNumber - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET BY CIN NUMBER METHOD EXECUTED END  EXECUTED TIME :: "+ (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<AcceptedRejectedContainer> saveAcceptedAndRejectedContainer(List<AcceptedRejectedContainerRequest> acceptedRejectedContainerRequests) {
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - saveAcceptedAndRejectedContainer - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(), "SAVE ACCEPTED AND REJECTED CONTAINER METHOD START");

        // Initialize response and list to hold successfully saved containers
        BaseResponse<AcceptedRejectedContainer> baseResponse = new BaseResponse<>();
        List<AcceptedRejectedContainer> acceptedRejectedContainers = new ArrayList<>();

        try {
            // Iterate over each request to process
            for (AcceptedRejectedContainerRequest request : acceptedRejectedContainerRequests) {
                // Check if an AcceptedRejectedContainer already exists for the given parameters
                Optional<AcceptedRejectedContainer> existingContainer = acceptedRejectedContainerRepository.findByIsDeletedAndIsAcceptedAndSubOrganizationIdAndAsnOrderLineIdAndAsnOrderLineItemId(
                        false, request.getIsAccepted(), loginUser.getSubOrgId(), request.getAsnLineId(), request.getItemId());

                // If no existing container is found, create and save a new one
                if (existingContainer.isEmpty()) {
                    AcceptedRejectedContainer newContainer = new AcceptedRejectedContainer();
                    newContainer.setIsAccepted(request.getIsAccepted());
                    newContainer.setAcceptedRejectedContainerQuantity(request.getAcceptedRejectedContainerQuantity());

                    // Set the ASN or PO line details based on the presence of ASN line ID
                    if (request.getAsnLineId() != null) {
                        ASNOrderLine asnOrderLine = asnOrderLineRepository.findByIsDeletedAndSubOrganizationIdAndIdAndItemId(false, loginUser.getSubOrgId(), request.getAsnLineId(), request.getItemId());
                        newContainer.setAsnOrderLine(asnOrderLine);
                        newContainer.setDock(asnOrderLine.getItem().getDock());
                    } else {
                        PurchaseOrderLine purchaseOrderLine = purchaseOrderLineRepository.findByIsDeletedAndSubOrganizationIdAndId(false, loginUser.getSubOrgId(), request.getPoLineId());
                        newContainer.setPurchaseOrderLine(purchaseOrderLine);
                        newContainer.setDock(purchaseOrderLine.getItem().getDock());
                    }

                    // Set common fields for the new container
                    newContainer.setIsDeleted(false);
                    newContainer.setCreatedBy(loginUser.getUserId());
                    newContainer.setCreatedOn(new Date());
                    newContainer.setSubOrganizationId(loginUser.getSubOrgId());
                    newContainer.setOrganizationId(loginUser.getOrgId());
                    newContainer.setModifiedBy(loginUser.getUserId());
                    newContainer.setModifiedOn(new Date());

                    // Save the new container to the repository
                    acceptedRejectedContainerRepository.save(newContainer);
                    acceptedRejectedContainers.add(newContainer);
                }
            }

            // Set successful response details
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setMessage("Successfully added accepted and rejected containers");
            baseResponse.setData(acceptedRejectedContainers);
            baseResponse.setLogId(loginUser.getLogId());
            log.info("LogId:{} - DockingService - saveAcceptedAndRejectedContainer - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(), "Successfully added accepted and rejected containers");

        } catch (Exception e) {
            // Handle any exceptions that occur during the process
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage("Failed to add accepted and rejected containers");
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            log.error("LogId:{} - DockingService - saveAcceptedAndRejectedContainer - UserId:{} - Failed to add accepted and rejected containers. Time: {} ms", loginUser.getLogId(), loginUser.getUserId(), (System.currentTimeMillis() - startTime), e);
        }

        // Log the total execution time of the method
        log.info("LogId:{} - DockingService - saveAcceptedAndRejectedContainer - UserId:{} - Method execution time: {} ms", loginUser.getLogId(), loginUser.getUserId(), (System.currentTimeMillis() - startTime));
        return baseResponse;
    }



    @Override
    public BaseResponse generateCRRNumber(String cinNumber, String dockId) {
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - generateCRRNumber - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(), "GET GENERATE CRR NUMBER METHOD START");

        BaseResponse baseResponse = new BaseResponse<>();
        String crr = null;

        try {
            // Retrieve the CodeMapper associated with the given CIN number
            CodeMapper codeMapper = codeMapperRepository.findByIsDeletedAndSubOrganizationIdAndCinBarcodeNumber(false, loginUser.getSubOrgId(), cinNumber);

            // If a CRR number already exists, retrieve it; otherwise, generate a new one
            crr = (codeMapper.getCrrBarcodeNumber() != null) ? codeMapper.getCrrBarcodeNumber() : generateAndSaveCrrNumber(codeMapper);

            // Retrieve common master records
            CommonMaster commonMaster = commonMasterRepository.findByIsDeletedAndTypeAndMasterValue(false, Const.ARCB, Const.CREATE);
            CommonMaster crrGenerated = commonMasterRepository.findByIsDeletedAndTypeAndMasterValue(false, Const.DQCS, Const.CRRGENERATED);

            // Process containers based on ASN or PO number
            if (codeMapper.getAsnNumber() != null) {
                processAcceptedRejectedContainers(codeMapper.getAsnNumber(), crr, commonMaster, crrGenerated);
            } else {
                processPurchaseOrderContainers(codeMapper.getPoNumber(), crr, commonMaster, crrGenerated);
            }

            // Return success response
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setData(new ArrayList<>());
            baseResponse.setMessage("GRR NUMBER GENERATED SUCCESSFULLY");
            log.info("LogId:{} - DockingService - generateCRRNumber - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(), "GRR NUMBER GENERATED SUCCESSFULLY");

        } catch (Exception e) {
            // Handle exceptions and return failure response
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage("FAILED TO GENERATE CRR NUMBER");
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            log.error("LogId:{} - DockingService - generateCRRNumber - UserId:{} - {} FAILED TO GENERATE CRR NUMBER TIME :: {}", loginUser.getLogId(), loginUser.getUserId(), (System.currentTimeMillis() - startTime), e);
        }

        log.info("LogId:{} - DockingService - generateCRRNumber - UserId:{} - GENERATE CRR NUMBER METHOD EXECUTED TIME :: {}", loginUser.getLogId(), loginUser.getUserId(), (System.currentTimeMillis() - startTime));
        return baseResponse;
    }

    /**
     * Generates and saves a new CRR number based on the CIN barcode number.
     */
    private String generateAndSaveCrrNumber(CodeMapper codeMapper) {
        String crr = crrNumber(codeMapper.getCinBarcodeNumber());
        codeMapper.setCrrBarcodeNumber(crr);
        codeMapper.setCrrCreateDate(new Date());
        codeMapperRepository.save(codeMapper);
        return crr;
    }

    /**
     * Processes containers based on ASN number, generating barcodes and updating their status.
     */
    private void processAcceptedRejectedContainers(String asnNumber, String crr, CommonMaster commonMaster, CommonMaster crrGenerated) {
        List<AcceptedRejectedContainer> containers = acceptedRejectedContainerRepository.findByIsDeletedAndSubOrganizationIdAndAsnOrderLineAsnHeadIdAsnNumberAndStatusIdIsNull(false, loginUser.getSubOrgId(), asnNumber);

        containers.forEach(container -> {
            generateAndSaveContainerBarcodes(container, crr, commonMaster);
            container.setStatus(crrGenerated);
            acceptedRejectedContainerRepository.save(container);
        });
    }

    /**
     * Processes containers based on PO number, generating barcodes and updating their status.
     */
    private void processPurchaseOrderContainers(String poNumber, String crr, CommonMaster commonMaster, CommonMaster crrGenerated) {
        List<PurchaseOrderLine> purchaseOrderLines = purchaseOrderLineRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadPurchaseOrderNumber(false, loginUser.getSubOrgId(), poNumber);

        purchaseOrderLines.forEach(poLine -> {
            List<AcceptedRejectedContainer> containers = acceptedRejectedContainerRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderLineId(false, loginUser.getSubOrgId(), poLine.getId());
            containers.forEach(container -> {
                generateAndSaveContainerBarcodes(container, crr, commonMaster);
                container.setStatus(crrGenerated);
                acceptedRejectedContainerRepository.save(container);
            });
        });
    }

    /**
     * Generates and saves barcodes for the accepted/rejected containers.
     */
    private void generateAndSaveContainerBarcodes(AcceptedRejectedContainer container, String crr, CommonMaster commonMaster) {
        IntStream.rangeClosed(1, container.getAcceptedRejectedContainerQuantity())
                .mapToObj(i -> createContainerBarcode(container, crr, i, commonMaster))
                .forEach(acceptedRejectedContainerBarcodeRepository::save);
    }

    /**
     * Creates a new AcceptedRejectedContainerBarcode based on the container's details.
     */
    private AcceptedRejectedContainerBarcode createContainerBarcode(AcceptedRejectedContainer container, String crr, int index, CommonMaster commonMaster) {
        String suffix = (index < 10) ? "00" + index : "0" + index;
        String statusCode = container.getIsAccepted() ? "C-A-" : "C-R-";
        String crrContainerCode = crr + "-" + container.getAsnOrderLine().getItem().getItemCode() + "-" + statusCode + suffix;

        AcceptedRejectedContainerBarcode barcode = new AcceptedRejectedContainerBarcode();
        barcode.setCrrContainerCode(crrContainerCode);
        barcode.setAcceptedRejectedContainer(container);
        barcode.setIsAccepted(container.getIsAccepted());
        barcode.setIsDeleted(false);
        barcode.setStatus(commonMaster);
        barcode.setSubOrganizationId(loginUser.getSubOrgId());
        barcode.setOrganizationId(loginUser.getOrgId());
        barcode.setCreatedBy(loginUser.getUserId());
        barcode.setCreatedOn(new Date());
        barcode.setModifiedBy(loginUser.getUserId());
        barcode.setModifiedOn(new Date());

        return barcode;
    }


    @Override
    public BaseResponse<DockByCRResponse> getAllDockByCRRGenerated(){
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getAllDockByCRRGenerated - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  GET ALL DOCK BY CRR GENERATED METHOD START");
        BaseResponse<DockByCRResponse> baseResponse=new BaseResponse<>();
        try{
            List<String> constant=new ArrayList<>();
            constant.add(Const.CRRGENERATED);
            constant.add(Const.DOCKPICKED);
            constant.add(Const.STAGINGPUT);
          List<AcceptedRejectedContainer> acceptedRejectedContainers=acceptedRejectedContainerRepository.findByIsDeletedAndSubOrganizationIdAndStatusMasterValueInAndDockOperatorId(false,loginUser.getSubOrgId(),constant,loginUser.getUserId());
            Map<Dock,List<Item>> dockListMap = acceptedRejectedContainers.stream()
                    .map(container -> container.getAsnOrderLine().getItem()).distinct()
                    .collect(Collectors.groupingBy(Item::getDock));
            List<DockByCRResponse> dockByCRResponses=new ArrayList<>();
            for (Map.Entry<Dock,List<Item>> entry:dockListMap.entrySet()) {
                DockByCRResponse dockByCRResponse=new DockByCRResponse();
                dockByCRResponse.setDock(entry.getKey());
                List<ItemDto> itemDtos= new ArrayList<>();
                for (Item item: entry.getValue()) {
                    ItemDto itemDto=new ItemDto();
                    itemDto.setId(item.getId());
                    itemDto.setItemCode(item.getItemCode());
                    itemDto.setItemName(item.getName());
                    List<AcceptedRejectedContainerBarcode> acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerDockIdAndAcceptedRejectedContainerAsnOrderLineItemId(false, loginUser.getSubOrgId(),entry.getKey().getId(), item.getId());
                    itemDto.setContainerQty(acceptedRejectedContainerBarcodes.get(0).getAcceptedRejectedContainer().getAsnOrderLine().getNumberOfContainer());
                    itemDto.setAsnLineId(acceptedRejectedContainerBarcodes.get(0).getAcceptedRejectedContainer().getAsnOrderLine().getId());
                    itemDto.setAcceptedContainerQty(acceptedRejectedContainerBarcodes.stream().filter(f->f.getIsAccepted().equals(true)).collect(Collectors.toList()).size());
                    itemDto.setRejectedContainerQty(acceptedRejectedContainerBarcodes.stream().filter(f->f.getIsAccepted().equals(false)).collect(Collectors.toList()).size());
                    itemDtos.add(itemDto);
                }
                dockByCRResponse.setItems(itemDtos);
                dockByCRResponses.add(dockByCRResponse);
            }
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setLogId(loginUser.getLogId());
            baseResponse.setData(dockByCRResponses);
            baseResponse.setMessage("SUCCESSFULLY FETCHED DOCK LIST ");
            log.info("LogId:{} - DockingService - getAllDockByCRRGenerated - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  SUCCESSFULLY FETCHED DOCK LIST ");
        }catch(Exception e){
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage(" FAILED TO FETCHED DOCK LIST ");
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockingService - getAllDockByCRRGenerated - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," FAILED TO FETCHED DOCK LIST TIME  :: "+ (endTime - startTime),e);
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getAllDockByCRRGenerated - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  GET ALL DOCK BY CRR GENERATED METHOD EXECUTED TIME  :: "+ (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<AcceptedRejectedContainerBarcode> getPickAcceptedAndRejectedContainer(Integer itemId ,Integer asnLineId, Integer poLineId){
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getPickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET PICK ACCEPTED AND REJECTED CONTAINER METHOD START");
        BaseResponse<AcceptedRejectedContainerBarcode> baseResponse=new BaseResponse<>();
        try{
            List<AcceptedRejectedContainerBarcode> acceptedRejectedContainerBarcodes=new ArrayList<>();
            if(asnLineId!=null) {
                acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndAcceptedRejectedContainerAsnOrderLineItemId(false, Const.CREATE, loginUser.getSubOrgId(), asnLineId, itemId);
            }else{
                acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndAcceptedRejectedContainerPurchaseOrderLineItemId(false, Const.CREATE, loginUser.getSubOrgId(), poLineId, itemId);
            }
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setData(acceptedRejectedContainerBarcodes);
            baseResponse.setMessage("SUCCESSFULLY FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST ");
            log.info("LogId:{} - DockingService - getPickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  ACCEPTED REJECTED CONTAINER BARCODE ");
        }catch(Exception e){
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage(" FAILED TO FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST ");
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockingService - getPickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," FAILED TO FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST TIME  :: "+ (endTime - startTime),e);
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getPickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET PICK ACCEPTED AND REJECTED CONTAINER METHOD EXECUTED TIME  :: "+ (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<AcceptedRejectedContainerBarcode> savePickAcceptedAndRejectedContainer(Integer itemId, Integer asnLineId, Integer poLine){
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - savePickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," SAVE PICK ACCEPTED AND REJECTED CONTAINER METHOD START");
        BaseResponse<AcceptedRejectedContainerBarcode> baseResponse=new BaseResponse<>();
        try{
            List<AcceptedRejectedContainerBarcode> acceptedRejectedContainerBarcodes=new ArrayList<>();
            if(asnLineId!=null) {
                acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndAcceptedRejectedContainerAsnOrderLineItemId(false, loginUser.getSubOrgId(),asnLineId, itemId);
            }else{
                acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndAcceptedRejectedContainerPurchaseOrderLineItemId(false, loginUser.getSubOrgId(),poLine, itemId);
            }
            CommonMaster commonMaster=commonMasterRepository.findByIsDeletedAndTypeAndMasterValue(false,Const.ARCB,Const.PICK);
            CommonMaster stagingPick=commonMasterRepository.findByIsDeletedAndTypeAndMasterValue(false,Const.DQCS,Const.DOCKPICKED);
            for (AcceptedRejectedContainerBarcode acceptedRejectedContainerBarcode:acceptedRejectedContainerBarcodes) {
                acceptedRejectedContainerBarcode.setStatus(commonMaster);
                acceptedRejectedContainerBarcode.getAcceptedRejectedContainer().setStatus(stagingPick);
                acceptedRejectedContainerBarcodeRepository.save(acceptedRejectedContainerBarcode);
            }
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setData(acceptedRejectedContainerBarcodes);
            baseResponse.setMessage("SUCCESSFULLY FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST ");
            log.info("LogId:{} - DockingService - savePickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  ACCEPTED REJECTED CONTAINER BARCODE ");
        }catch(Exception e){
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage(" FAILED TO FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST ");
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockingService - savePickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," FAILED TO FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST TIME  :: "+ (endTime - startTime),e);
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - savePickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," SAVE PICK ACCEPTED AND REJECTED CONTAINER METHOD EXECUTED TIME  :: "+ (endTime - startTime));
        return baseResponse;
    }
    @Override
    public BaseResponse<AcceptedRejectedContainerBarcode> getPutAcceptedAndRejectedContainer(Integer itemId, Integer asnLineId, Integer poLine){
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getPutAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET PUT ACCEPTED AND REJECTED CONTAINER METHOD START");
        BaseResponse<AcceptedRejectedContainerBarcode> baseResponse=new BaseResponse<>();
        List<AcceptedRejectedContainerBarcode>acceptedRejectedContainerBarcodes=new ArrayList<>();
        try{
            if(asnLineId!=null) {
                acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndAcceptedRejectedContainerAsnOrderLineItemId(false, Const.PICK, loginUser.getSubOrgId(), asnLineId, itemId);
            }else{
                acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndAcceptedRejectedContainerPurchaseOrderLineItemId(false, Const.PICK, loginUser.getSubOrgId(), poLine, itemId);
            }
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setData(acceptedRejectedContainerBarcodes);
            baseResponse.setMessage("SUCCESSFULLY FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST ");
            log.info("LogId:{} - DockingService - getPutAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  ACCEPTED REJECTED CONTAINER BARCODE ");
        }catch(Exception e){
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage(" FAILED TO FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST ");
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockingService - getPutAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," FAILED TO FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST TIME  :: "+ (endTime - startTime),e);
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getPutAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET PUT ACCEPTED AND REJECTED CONTAINER METHOD EXECUTED TIME  :: "+ (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<AcceptedRejectedContainerBarcode> savePutAcceptedAndRejectedContainer(Integer itemId, Integer asnLineId, Integer poLineId,Integer stagingAreaId,Boolean status){
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - savePutAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," SAVE PUT ACCEPTED AND REJECTED CONTAINER METHOD START");
        BaseResponse<AcceptedRejectedContainerBarcode> baseResponse=new BaseResponse<>();
        List<AcceptedRejectedContainerBarcode>acceptedRejectedContainerBarcodes=new ArrayList<>();
        try{
            if(asnLineId!=null) {
                acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndIsAcceptedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndAcceptedRejectedContainerAsnOrderLineItemId(false,status, Const.PICK, loginUser.getSubOrgId(), asnLineId, itemId);
            }else{
                acceptedRejectedContainerBarcodes = acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndIsAcceptedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndAcceptedRejectedContainerPurchaseOrderLineItemId(false,status, Const.PICK, loginUser.getSubOrgId(), poLineId, itemId);
            }
            AcceptedRejectedStagingArea acceptedRejectedStagingAreas=acceptedRejectedStagingAreaRepository.findByIsDeletedAndSubOrganizationIdAndId(false,loginUser.getSubOrgId(),stagingAreaId);
            CommonMaster commonMaster=commonMasterRepository.findByIsDeletedAndTypeAndMasterValue(false,Const.ARCB,Const.PUT);
            CommonMaster stagingPut=commonMasterRepository.findByIsDeletedAndTypeAndMasterValue(false,Const.DQCS,Const.STAGINGPUT);
            for (AcceptedRejectedContainerBarcode acceptedRejectedContainerBarcode:acceptedRejectedContainerBarcodes) {
                acceptedRejectedContainerBarcode.setStatus(commonMaster);
                acceptedRejectedContainerBarcode.setAcceptedRejectedStagingArea(acceptedRejectedStagingAreas);
                acceptedRejectedContainerBarcode.getAcceptedRejectedContainer().setStatus(stagingPut);
                acceptedRejectedContainerBarcode.getAcceptedRejectedContainer().setDockOperator(null);
                acceptedRejectedContainerBarcodeRepository.save(acceptedRejectedContainerBarcode);
            }
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setData(acceptedRejectedContainerBarcodes);
            baseResponse.setMessage("SUCCESSFULLY FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST ");
            log.info("LogId:{} - DockingService - savePutAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  ACCEPTED REJECTED CONTAINER BARCODE ");
        }catch(Exception e){
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage(" FAILED TO FETCHED SAVE ACCEPTED REJECTED CONTAINER BARCODE ");
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockingService - savePutAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," FAILED TO FETCHED SAVE ACCEPTED REJECTED CONTAINER BARCODE TIME  :: "+ (endTime - startTime),e);
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - savePutAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," SAVE PUT ACCEPTED AND REJECTED CONTAINER METHOD EXECUTED TIME  :: "+ (endTime - startTime));
        return baseResponse;
    }
    public String crrNumber(String cin){
        return cin.replace("CIN","CRR");

    }

    @Override
    public BaseResponse<AcceptedRejectedStagingArea> getStagingAreaByDock(Integer dockId){
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getStagingAreaByDock - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET STAGING AREA BY DOCK METHOD START");
        BaseResponse<AcceptedRejectedStagingArea> baseResponse=new BaseResponse<>();
        try{
            List<AcceptedRejectedStagingArea> acceptedRejectedStagingAreas = acceptedRejectedStagingAreaRepository.findByIsDeletedAndSubOrganizationIdAndStagingAreaDockId(false, loginUser.getSubOrgId(), dockId);
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setData(acceptedRejectedStagingAreas);
            baseResponse.setMessage(" SUCCESSFULLY FETCHED STAGING LIST ");
            log.info("LogId:{} - DockingService - getPickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId(),"  SUCCESSFULLY FETCHED STAGING LIST ");
        }catch(Exception e){
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage(" FAILED TO FETCHED ACCEPTED REJECTED CONTAINER BARCODE LIST ");
            baseResponse.setData(new ArrayList<>());
            baseResponse.setLogId(loginUser.getLogId());
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - DockingService - getPickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," FAILED TO STAGING LIST TIME  :: "+ (endTime - startTime),e);
        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getPickAcceptedAndRejectedContainer - UserId:{} - {}",loginUser.getLogId(),loginUser.getUserId()," GET STAGING AREA BY DOCK METHOD EXECUTED TIME  :: "+ (endTime - startTime));
        return baseResponse;
    }
    @Override
    public BaseResponse<Users> getDockOperator(Integer dockId) {
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getDockOperator - UserId:{} - {}",
                loginUser.getLogId(), loginUser.getUserId(), "GET DOCK OPERATOR METHOD START");

        BaseResponse<Users> baseResponse = new BaseResponse<>();

        try {
            // Fetch containers with a valid dock and dock operator
            List<AcceptedRejectedContainer> acceptedRejectedContainers =
                    acceptedRejectedContainerRepository.findByIsDeletedAndSubOrganizationIdAndDockIdIsNotNull(
                            false, loginUser.getSubOrgId());

            // Separate the specific dock operator and other operators using streams
            Users specificDockOperator = acceptedRejectedContainers.stream()
                    .filter(container -> container.getDock().getId().equals(dockId) && container.getDockOperator() != null)
                    .map(AcceptedRejectedContainer::getDockOperator)
                    .findFirst()
                    .orElse(null);

            Set<Users> otherDockOperators = acceptedRejectedContainers.stream()
                    .filter(container -> !container.getDock().getId().equals(dockId) && container.getDockOperator() != null)
                    .map(AcceptedRejectedContainer::getDockOperator)
                    .collect(Collectors.toSet());

            // Fetch all active dock operators and filter out the ones already in otherDockOperators
            List<Users> dockOperators;
            if (specificDockOperator != null) {
                dockOperators = Collections.singletonList(specificDockOperator);
            } else {
                dockOperators = userRepository.findByIsDeletedAndSubOrganizationIdAndIsActiveAndModuleUserLicenceKeyLicenceLineSubModuleSubModuleCode(
                                false, loginUser.getSubOrgId(), true, "DOPT")
                        .stream()
                        .filter(dockOp -> !otherDockOperators.contains(dockOp))
                        .collect(Collectors.toList());
            }

            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setData(dockOperators);
            baseResponse.setMessage("SUCCESSFULLY FETCHED DOCK OPERATOR LIST");
            log.info("LogId:{} - DockingService - getDockOperator - UserId:{} - {}",
                    loginUser.getLogId(), loginUser.getUserId(), "SUCCESSFULLY FETCHED DOCK OPERATOR LIST");

        } catch (Exception e) {
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage("FAILED TO FETCH DOCK OPERATOR LIST");
            baseResponse.setData(Collections.emptyList());
            baseResponse.setLogId(loginUser.getLogId());
            log.error("LogId:{} - DockingService - getDockOperator - UserId:{} - {}",
                    loginUser.getLogId(), loginUser.getUserId(),
                    "FAILED TO FETCH DOCK OPERATOR TIME  :: " + (System.currentTimeMillis() - startTime), e);
        }

        log.info("LogId:{} - DockingService - getDockOperator - UserId:{} - {}",
                loginUser.getLogId(), loginUser.getUserId(),
                "GET DOCK OPERATOR METHOD EXECUTED TIME  :: " + (System.currentTimeMillis() - startTime));

        return baseResponse;
    }

    @Override
    public BaseResponse<AcceptedRejectedContainer> setDockOperatorOnDock(Integer dockId, Integer dockOperatorId) {
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - DockingService - getDockOperator - UserId:{} - {}",
                loginUser.getLogId(), loginUser.getUserId(), "GET ASSIGN DOCK OPERATOR IN DOCK START");

        BaseResponse<AcceptedRejectedContainer> baseResponse = new BaseResponse<>();

        try {
            // Fetch containers with a valid dock and dock operator
            List<AcceptedRejectedContainer> acceptedRejectedContainers =
                    acceptedRejectedContainerRepository.findByIsDeletedAndSubOrganizationIdAndDockId(
                            false, loginUser.getSubOrgId(),dockId);

            for (AcceptedRejectedContainer acceptedRejectedContainer:acceptedRejectedContainers) {
                acceptedRejectedContainer.setDockOperator(userRepository.findByIsDeletedAndSubOrganizationIdAndId(false,loginUser.getSubOrgId(),dockOperatorId));
                acceptedRejectedContainerRepository.save(acceptedRejectedContainer);
            }
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setData(acceptedRejectedContainers);
            baseResponse.setMessage("SUCCESSFULLY ASSIGN DOCK OPERATOR IN DOCK ");
            log.info("LogId:{} - DockingService - getDockOperator - UserId:{} - {}",
                    loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY ASSIGN DOCK OPERATOR IN DOCK ");

        } catch (Exception e) {
            baseResponse.setCode(0);
            baseResponse.setStatus(500);
            baseResponse.setMessage("FAILED TO FETCH ASSIGN DOCK OPERATOR IN DOCK ");
            baseResponse.setData(Collections.emptyList());
            baseResponse.setLogId(loginUser.getLogId());
            log.error("LogId:{} - DockingService - getDockOperator - UserId:{} - {}",
                    loginUser.getLogId(), loginUser.getUserId(),
                    "FAILED TO FETCH STAGING LIST TIME  :: " + (System.currentTimeMillis() - startTime), e);
        }

        log.info("LogId:{} - DockingService - getDockOperator - UserId:{} - {}",
                loginUser.getLogId(), loginUser.getUserId(),
                "GET ASSIGN DOCK OPERATOR IN DOCK METHOD EXECUTED TIME  :: " + (System.currentTimeMillis() - startTime));

        return baseResponse;
    }

    @Override
    public BaseResponse<ItemDto> getAllItemCustomResponse(){
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - ItemServicesImpl - getAllItem - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(),"ITEM LIST FETCHED METHOD START ");
        BaseResponse<ItemDto> baseResponse=new BaseResponse<>();
        try{
            List<String> status=new ArrayList<>();
            status.add(Const.CRRGENERATED);
            status.add(Const.STAGINGPUT);
            List<AcceptedRejectedContainer> acceptedRejectedContainers=acceptedRejectedContainerRepository.findByIsDeletedAndSubOrganizationIdAndStatusMasterValueIn(false,loginUser.getSubOrgId(),status);
            List<ItemDto> itemsResponses=new ArrayList<>();
            for (AcceptedRejectedContainer acceptedRejectedContainer:acceptedRejectedContainers) {
                ItemDto itemDto=new ItemDto();
                itemDto.setId(acceptedRejectedContainer.getAsnOrderLine().getItem().getId());
                itemDto.setItemName(acceptedRejectedContainer.getAsnOrderLine().getItem().getName());
                itemDto.setPoLineId(acceptedRejectedContainer.getAsnOrderLine().getId());
                if(acceptedRejectedContainer.getPurchaseOrderLine()!=null) {
                    itemDto.setPoLineId(acceptedRejectedContainer.getPurchaseOrderLine().getId());
                }
                itemDto.setItemCode(acceptedRejectedContainer.getAsnOrderLine().getItem().getItemCode());
                itemsResponses.add(itemDto);
            }
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setMessage(" SUCCESSFULLY FETCHED ITEMS ");
            baseResponse.setData(itemsResponses);
            baseResponse.setLogId(loginUser.getLogId());
            log.info("LogId:{} - ItemServicesImpl - getAllItem - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId());
        }catch (Exception ex){
            baseResponse.setCode(0);
            baseResponse.setStatus(200);
            baseResponse.setMessage(" FAILED TO FETCHED ITEMS ");
            baseResponse.setLogId(loginUser.getLogId());
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - ItemServicesImpl - getAllItem - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(), (endTime - startTime),ex);

        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - ItemServicesImpl - getAllItem - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId()," ITEM LIST FETCHED TIME :" + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<CRRResponse> getCRRBarcode(Boolean isAccepted, Integer asnLineId, Integer poLineId){
        long startTime = System.currentTimeMillis();
        log.info("LogId:{} - ItemServicesImpl - getAllItem - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(),"ITEM LIST FETCHED METHOD START ");
        BaseResponse<CRRResponse> baseResponse=new BaseResponse<>();
        try{
            List<String> status=new ArrayList<>();
            List<AcceptedRejectedContainerBarcode> acceptedRejectedContainerBarcodes=new ArrayList<>();
            if(asnLineId!=null){
                acceptedRejectedContainerBarcodes=acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndIsAccepted(false,loginUser.getSubOrgId(),asnLineId,isAccepted);
            }else {
                acceptedRejectedContainerBarcodes=acceptedRejectedContainerBarcodeRepository.findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndIsAccepted(false,loginUser.getSubOrgId(),poLineId,isAccepted);
            }

            List<CRRResponse> crrResponses=new ArrayList<>();
            for (AcceptedRejectedContainerBarcode acceptedRejectedContainerBarcode:acceptedRejectedContainerBarcodes) {
                CRRResponse crrResponse=new CRRResponse();
                crrResponse.setId(acceptedRejectedContainerBarcode.getId());
                crrResponse.setCrrBarcode(acceptedRejectedContainerBarcode.getCrrContainerCode());
                crrResponses.add(crrResponse);
            }
            baseResponse.setCode(1);
            baseResponse.setStatus(200);
            baseResponse.setMessage(" SUCCESSFULLY FETCHED ITEMS ");
            baseResponse.setData(crrResponses);
            baseResponse.setLogId(loginUser.getLogId());
            log.info("LogId:{} - ItemServicesImpl - getAllItem - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId());
        }catch (Exception ex){
            baseResponse.setCode(0);
            baseResponse.setStatus(200);
            baseResponse.setMessage(" FAILED TO FETCHED ITEMS ");
            baseResponse.setLogId(loginUser.getLogId());
            long endTime = System.currentTimeMillis();
            log.error("LogId:{} - ItemServicesImpl - getAllItem - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(),ex);

        }
        long endTime = System.currentTimeMillis();
        log.info("LogId:{} - ItemServicesImpl - getAllItem - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId()," ITEM LIST FETCHED TIME :" + (endTime - startTime));
        return baseResponse;
    }
}