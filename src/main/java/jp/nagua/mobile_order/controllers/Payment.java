package jp.nagua.mobile_order.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.PaymentData;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class Payment {

    private static final String API_KEY = "a_RBasZaIlMJ_aeq5";
    private static final String API_SECRET = "wDUIGYbofuJ4Ea724KgH9kPMD5XF5auxc3U5+kFYpe8=";
    private static final String MERCHANT_ID = "823676450571870208";


    @PostMapping(value = "/payment")
    @ResponseBody
    public String payment(@RequestBody String str) throws ApiException {
        JsonObject json = JsonParser.parseString(str).getAsJsonObject();
        PaymentData data = MobileOrderApplication.getPaymentData(json.get("uuid").getAsString());
        if(json.get("type").getAsString().equals("cash")) {
            if(MobileOrderApplication.runPayment(data, json.get("deposit").getAsInt()) == true) {
                Map<Object, Object> jsonMap = new HashMap<>();
                jsonMap.put("uuid", data.getUUID());
                jsonMap.put("status", data.getStatus());
                jsonMap.put("total", data.getTotal());
                jsonMap.put("deposit", data.getDeposit());
                jsonMap.put("change", data.getChange());
                MobileOrderApplication.getOrderContent(data.getUUID().toString()).setStatus("paid");
                return new Gson().toJson(jsonMap);
            }
            return "failed";
        } else if(json.get("type").getAsString().equals("paypay")) {
            ApiClient apiClient = new Configuration().getDefaultApiClient();
            apiClient.setProductionMode(false);
            apiClient.setApiKey(API_KEY);
            apiClient.setApiSecretKey(API_SECRET);
            apiClient.setAssumeMerchant(MERCHANT_ID);
            QRCode qrCode = new QRCode();
            qrCode.setAmount(new MoneyAmount().amount(data.getTotal()).currency(MoneyAmount.CurrencyEnum.JPY));
            qrCode.setMerchantPaymentId(json.get("uuid").getAsString());
            qrCode.setCodeType("ORDER_QR");
            qrCode.setOrderDescription("モバイルオーダー支払い");
            qrCode.isAuthorization(false);
            qrCode.setRedirectUrl("http://localhost:3000/customer");
            PaymentApi apiInstance = new PaymentApi(apiClient);
            QRCodeDetails response = apiInstance.createQRCode(qrCode);
            data.setStatus("paypayPending");
            data.setURL(response.getData().getUrl());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    PaymentApi apiInstance = new PaymentApi(apiClient);
                    while(true) {
                        try {
                            PaymentDetails response = apiInstance.getCodesPaymentDetails(json.get("uuid").getAsString());
                            if(response.getData().getStatus().toString().equals("COMPLETED")) {
                                break;
                            }
                            Thread.sleep(1000);
                        } catch (ApiException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    MobileOrderApplication.runPayment(data, data.getTotal());
                    MobileOrderApplication.getOrderContent(data.getUUID().toString()).setStatus("paid");
                }
            });
            thread.start();
            return response.getData().getUrl();
        }
        return "failed";
    }
}
