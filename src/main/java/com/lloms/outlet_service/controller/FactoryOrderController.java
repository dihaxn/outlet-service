package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.request.FactoryOrderRequestDTO;
import com.lloms.outlet_service.dto.response.FactoryOrderResDTO;
import com.lloms.outlet_service.service.FactoryOrderService;
import com.lloms.outlet_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lloms.outlet_service.enums.FactoryOrderStatus;

import java.util.List;

@RestController
@RequestMapping("api/v1/fac-order")
@CrossOrigin
@AllArgsConstructor
public class FactoryOrderController {
    private final FactoryOrderService factoryOrderService;
    @PostMapping("")
    public ResponseEntity<StandardResponse> saveFacOrder(@RequestBody FactoryOrderRequestDTO factoryOrderRequestDTO) {
        System.out.println(factoryOrderRequestDTO);
        factoryOrderService.saveFacOrder(factoryOrderRequestDTO);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<StandardResponse> getFacOrders(@RequestParam FactoryOrderStatus status) {//any=>all Confirmed Canceled
        List<FactoryOrderResDTO> factoryOrderResDTOS= factoryOrderService.getFacOrderByStatus(status);
        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setData(factoryOrderResDTOS);
        standardResponse.setMessage("success");
        standardResponse.setCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);
    }
}
