package com.hfad.circle2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
import java.util.List;

public class ChoiceActivity extends AppCompatActivity implements PurchasesUpdatedListener {

    ImageView bigCircle;
    String typeOfCircle;
    Intent data = new Intent();
    Animation anim;
    private Button buttonBuyProduct;
    private BillingClient billingClient;
    private List<String> skuList = new ArrayList<>();
    private String sku1 = "com.scorp.circle_black2";
    private String sku2 = "com.scorp.circle_red2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        bigCircle = (ImageView) findViewById(R.id.bigCircle);
        buttonBuyProduct = (Button) findViewById(R.id.btnBuy);
        buttonBuyProduct.setEnabled(false);
        getValueOfCircle("circle_black");

        Boolean b_sku1 = getBoolFromPref(this, "myPref", sku1);
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
        }
    }

    private void setupBillingClient() {
        billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    loadAllSkus();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                billingClient.startConnection(this);
            }
        });
    }

    private void loadAllSkus() {
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
                            } else if (skuDetails.getSku().equals(sku2)) {
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
    }

    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
        int responseCode = billingResult.getResponseCode();

        if (responseCode == BillingClient.BillingResponseCode.OK && purchases != null){
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (responseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            setBoolInPref(this, "myPref", sku1, true);
        } else if (responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {

        }
    }

    private void handlePurchase(Purchase purchase) {
        if (purchase.getSku().equals(sku1)) {
            setBoolInPref(this, "myPref", sku1, true);
            Toast.makeText(this, "You've got the black one", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean getBoolFromPref(Context context, String prefName, String constantName){
        SharedPreferences pref = context.getSharedPreferences(prefName, 0);
        return pref.getBoolean(constantName, false);
    }

    private void setBoolInPref(Context context, String prefName, String constantName, Boolean val){
        SharedPreferences pref = context.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.commit();
    }

    public void onClickCircleBlack(View view) {
        bigCircle.setImageResource(R.drawable.circle_black_big);
        getValueOfCircle("circle_black");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleBlack2(View view) {
        bigCircle.setImageResource(R.drawable.circle_black2_big);
        getValueOfCircle("circle_black2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleBlue(View view) {
        bigCircle.setImageResource(R.drawable.circle_blue_big);
        getValueOfCircle("circle_blue");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleBlue2(View view) {
        bigCircle.setImageResource(R.drawable.circle_blue2_big);
        getValueOfCircle("circle_blue2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleRed(View view) {
         bigCircle.setImageResource(R.drawable.circle_red_big);
        getValueOfCircle("circle_red");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleRed2(View view) {
        bigCircle.setImageResource(R.drawable.circle_red2_big);
        getValueOfCircle("circle_red2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCirclePurple(View view) {
        bigCircle.setImageResource(R.drawable.circle_purple_big);
        getValueOfCircle("circle_purple");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCirclePurpleValued(View view) {
        bigCircle.setImageResource(R.drawable.circle_purple2_big);
        getValueOfCircle("circle_purple2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    private void getValueOfCircle(String message){
        data.putExtra(MainActivity.ACCESS_MESSAGE, message);
    }
    public void OnClickChoiceCircleButton(View view) {
        setResult(RESULT_OK, data);
        finish();
    }
}
