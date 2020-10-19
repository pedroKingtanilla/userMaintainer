package com.globallogic.userMaintainer.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class UserControllerTest extends Specification {
/*
    //FinancialProductsService financialProductsService = Mock(FinancialProductsService)
    HttpHeaders httpHeaders
    def setup(){
        dataBaseResponseDto = new DataBaseResponseDto()
        httpHeaders = new HttpHeaders()
        insuranceDto = new InsuranceDto()
        headerParamsDto = new HeaderParamsDto()
        quotationResponseDto = new QuoteResponseDto()
        financialProductsDto = new FinancialProductsDto()
        financialProductsController = new FinancialProductsController(financialProductsService)
        insuranceDto.setSubProduct("VCFDSA")
        insuranceDto.setPlan("1")
        financialProductsDto.setInsurance(insuranceDto)
    }

    def "test post controller method"() {
        given:
            httpHeaders.add("transactionId","12345")
            httpHeaders.add("channel","BANCO")
            httpHeaders.add("country","CL")
            httpHeaders.add("flow","OMNI_ONBOARDING_WEB")
            httpHeaders.add("source","WEB_OMNI2")
            httpHeaders.add("executiveId","7777777")
            httpHeaders.add("branchId","520")
            financialProductsService.getFinancialProducts(_ as FinancialProductsDto, _ as HeaderParamsDto, _ as DataBaseResponseDto) >> ResponseEntity.status(HttpStatus.OK).body(quotationResponseDto)
        when:
            def res = financialProductsController.getFinancialProducts(financialProductsDto, httpHeaders)
        then:
            res.statusCode == HttpStatus.OK
    }

    def "test validate headers method Bad Request"() {
        given:
            QuoteResponseDto quotationResponseDto = new QuoteResponseDto()
            quotationResponseDto.setMessage("Bad Request")
            httpHeaders.add("transactionId","")
            httpHeaders.add("channel","")
            httpHeaders.add("country","")
            httpHeaders.add("flow","")
            httpHeaders.add("source","")
            financialProductsService.getFinancialProducts(_ as FinancialProductsDto, _ as HeaderParamsDto, _ as DataBaseResponseDto)  >> new ResponseEntity(quotationResponseDto, HttpStatus.BAD_REQUEST)
        when:
        ResponseEntity res = financialProductsController.getFinancialProducts(financialProductsDto, httpHeaders)
        then:
            res.statusCode == HttpStatus.BAD_REQUEST
    }*/
}
