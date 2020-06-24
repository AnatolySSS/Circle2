package com.hfad.circle2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoiceActivity extends AppCompatActivity /*implements PurchasesUpdatedListener*/ {

    ImageView bigCircle;
    String typeOfCircle;
    Intent data = new Intent();
    Animation anim;
//    private Button buttonBuyProduct;
    private BillingClient billingClient;
    private Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();
//    private List<String> skuList = new ArrayList<>();
    private String circle_red2 = "com.scorp.circle_red2";
    private String circle_blue2 = "com.scorp.circle_blue2";
    private String circle_purple2 = "com.scorp.circle_purple2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        bigCircle = (ImageView) findViewById(R.id.bigCircle);
        /*buttonBuyProduct = (Button) findViewById(R.id.btnBuy);
        buttonBuyProduct.setEnabled(false);*/
        getValueOfCircle("circle_black");

        initBilling();

        //Old code
        /*Boolean b_sku1 = getBoolFromPref(this, "myPref", sku1);
        Boolean b_sku2 = getBoolFromPref(this, "myPref", sku2);

        if (b_sku1) {
            buttonBuyProduct.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "You've already got it", Toast.LENGTH_SHORT).show();
        } else {
            skuList.add(sku1);
            setupBillingClient();
        }

        if (b_sku2) {
            buttonBuyProduct.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "You've already got it", Toast.LENGTH_SHORT).show();
        } else {
            skuList.add(sku2);
            setupBillingClient();
        }*/
    }

    private void initBilling() {
        final BillingClient.Builder builder = BillingClient.newBuilder(this);
        builder.enablePendingPurchases();

        billingClient = builder.setListener(new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
                int responseCode = billingResult.getResponseCode();
                if (responseCode == BillingClient.BillingResponseCode.OK && purchases != null){
                    //for (Purchase purchase : purchases) {
                        payComplete();
                    //}
                } /*else if (responseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {

                } else if (responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {

                }*/
            }
        }).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                int responseCode = billingResult.getResponseCode();
                if (responseCode == BillingClient.BillingResponseCode.OK) {
                    querySkuDetails();
                    List<Purchase> purchasesList = queryPurchases();

                    for (int i = 0; i < purchasesList.size(); i++) {
                        String purchaseId = purchasesList.get(i).getSku();
                        if(TextUtils.equals(circle_red2, purchaseId)) {
                            payComplete();
                        } else if (TextUtils.equals(circle_blue2, purchaseId)) {
                            payComplete();
                        }
                        else if (TextUtils.equals(circle_purple2, purchaseId)) {
                            payComplete();
                        }
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                billingClient.startConnection(this);
            }
        });
    }

    //New code
    private void querySkuDetails() {
        SkuDetailsParams.Builder skuDetailsParamsBuilder = SkuDetailsParams.newBuilder();
        List<String> skuList = new ArrayList<>();
        skuList.add(circle_red2);
        skuList.add(circle_blue2);
        skuList.add(circle_purple2);
        skuDetailsParamsBuilder.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(skuDetailsParamsBuilder.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                    for (SkuDetails skuDetails : skuDetailsList) {
                        mSkuDetailsMap.put(skuDetails.getSku(), skuDetails);
                    }
                }
            }
        });
    }

    //Old code
    /*private void loadAllSkus() {
        if(billingClient.isReady()){
            final SkuDetailsParams params = SkuDetailsParams.newBuilder()
                    .setSkusList(skuList)
                    .setType(BillingClient.SkuType.INAPP)
                    .build();

            billingClient.querySkuDetailsAsync(params, new SkuDetailsResponseListener() {
                @Override
                public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        for (Object skuDetailsObject : skuDetailsList) {
                            final SkuDetails skuDetails = (SkuDetails) skuDetailsObject;
                            if (skuDetails.getSku().equals(sku1)) {
                                buttonBuyProduct.setEnabled(true);
                                buttonBuyProduct.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        BillingFlowParams params = BillingFlowParams
                                                .newBuilder()
                                                .setSkuDetails(skuDetails)
                                                .build();
                                        billingClient.launchBillingFlow(ChoiceActivity.this, params);
                                    }
                                });
                            }
                        }
                    }
                }
            });
        }
    }*/

    private List<Purchase> queryPurchases() {
        Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP);
        return purchasesResult.getPurchasesList();
    }

    //New code
    private void launchBilling(String skuId) {
        SkuDetails skuDetails = mSkuDetailsMap.get(skuId);
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(mSkuDetailsMap.get(skuId))
                .build();
        billingClient.launchBillingFlow(this, billingFlowParams);
    }

    //New code
    private void payComplete() {
        Toast.makeText(this, "You've got the new one", Toast.LENGTH_SHORT).show();
    }

    public void OnClickChoiceCircleButton(View view) {
        if(false) {
            setResult(RESULT_OK, data);
            finish();
        } else {
            if (data.hasExtra("circle_red2")) {
                launchBilling(circle_red2);
            } else if (data.hasExtra("circle_blue2")) {
                launchBilling(circle_blue2);
            } else if (data.hasExtra("circle_purple2")) {
                launchBilling(circle_purple2);
            }
        }
    }

    public void onClickCircleBlack2(View view) {
        bigCircle.setImageResource(R.drawable.circle_black2_big);
        getValueOfCircle("circle_black2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleBlue2(View view) {
        bigCircle.setImageResource(R.drawable.circle_locked);
        getValueOfCircle("circle_blue2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleRed2(View view) {
        bigCircle.setImageResource(R.drawable.circle_locked);
        getValueOfCircle("circle_red2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCirclePurpleValued(View view) {
        bigCircle.setImageResource(R.drawable.circle_locked);
        getValueOfCircle("circle_purple2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    private void getValueOfCircle(String message){
        data.putExtra(/*MainActivity.ACCESS_MESSAGE, */message, "");
    }

    //Old code
    /*private void handlePurchase(Purchase purchase) {
        if (purchase.getSku().equals(sku1)) {
            setBoolInPref(this, "myPref", sku1, true);
            Toast.makeText(this, "You've got the black one", Toast.LENGTH_SHORT).show();
        }
    }

    //Old code
    private Boolean getBoolFromPref(Context context, String prefName, String constantName){
        SharedPreferences pref = context.getSharedPreferences(prefName, 0);
        return pref.getBoolean(constantName, false);
    }

    //Old code
    private void setBoolInPref(Context context, String prefName, String constantName, Boolean val){
        SharedPreferences pref = context.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.commit();
    }*/
}

