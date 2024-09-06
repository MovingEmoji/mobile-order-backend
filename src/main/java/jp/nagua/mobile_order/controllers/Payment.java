package jp.nagua.mobile_order.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
        ApiClient apiClient = new Configuration().getDefaultApiClient();
        apiClient.setProductionMode(false);
        apiClient.setApiKey(API_KEY);
        apiClient.setApiSecretKey(API_SECRET);
        apiClient.setAssumeMerchant(MERCHANT_ID);
        QRCode qrCode = new QRCode();
        qrCode.setAmount(new MoneyAmount().amount(json.get("total").getAsInt()).currency(MoneyAmount.CurrencyEnum.JPY));
        qrCode.setMerchantPaymentId(json.get("uuid").getAsString());
        qrCode.setCodeType("ORDER_QR");
        qrCode.setOrderDescription("モバイルオーダー支払い");
        qrCode.isAuthorization(false);
        PaymentApi apiInstance = new PaymentApi(apiClient);
        QRCodeDetails response = apiInstance.createQRCode(qrCode);
        return response.getData().getUrl();
    }
}
