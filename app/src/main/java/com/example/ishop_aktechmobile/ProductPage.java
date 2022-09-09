package com.example.ishop_aktechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class ProductPage extends AppCompatActivity {

    ImageView displayimage;
    TextView productname , productprice, rating, reviewnums, desc;
    String hehe = "test";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_product_page);





        Intent intent = getIntent();
        String barcode = intent.getStringExtra("barcode");



        displayimage = findViewById(R.id.image);
        reviewnums = findViewById(R.id.revnum);
        productprice = findViewById(R.id.price);
        productname = findViewById(R.id.productname);
        rating = findViewById(R.id.ratingtxt);
        desc = findViewById(R.id.priceproduct);



        //getprice(barcode);








        //Glide.with(this).load(getprice(barcode)).into(displayimage);
        runThread(barcode);
        getprice(barcode);






//https://www.amazon.com/s?k=9781474611565

















    }


    public void runThread(String barcode) {







        new Thread() {
            public void run() {




                    try {
                        runOnUiThread(new Runnable() {

                            Document doc, document;   //also tried .post()

                            {
                                try {

                                    String searchURL = "https://www.google.com/search?q=9781474611565&num=1";
                                    //without proper User-Agent, we will get 403 error
                                    //productname.setText("iufiub");
                                    String url = "https://www.bing.com/images/search?q="+barcode;
                                    Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");




                                    con.get();

                                    Connection.Request request = con.request();
                                    Map<String, String> cookies = request.cookies();
                                    for(String cookieName : cookies.keySet()) {
                                        //filter cookies you want to stay in map
                                        request.removeCookie(cookieName);
                                    }

                                    Document doc = con.get();



                                    Element divWithStars = doc.select("a.inflnk[aria-label]").first();
                                    String ariaLabel = divWithStars.attr("aria-label");

                                    String nameting = ariaLabel.toUpperCase(Locale.ROOT);

                                    //productname.setText(imgsrc);

                                    productname.setText(nameting);


















                                    //Glide.with(getApplicationContext()).load("https://www.bibdsl.co.uk/imagegallery2/BDS/201844/9781474611565_1.jpg").into(displayimage);








                                    //String linkText = results.text();



                                } catch (IOException e) {
                                    e.printStackTrace();

                                }


                            }






                            @Override
                            public void run() {







                            }
                        });
                        //Thread.sleep(30);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
        }.start();






    }


    public void getprice(String barcode) {



        new Thread() {

            public void run() {




                try {
                    runOnUiThread(new Runnable() {

                        Document doc, document;   //also tried .post()

                        {
                            try {


                                String url = "https://www.amazon.co.uk/s?k="+barcode;
                                Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");




                                con.get();

                                Connection.Request request = con.request();
                                Map<String, String> cookies = request.cookies();
                                for(String cookieName : cookies.keySet()) {
                                    //filter cookies you want to stay in map
                                    request.removeCookie(cookieName);
                                }

                                Document doc = con.get();



                                Element elements = doc.select("span.a-offscreen").first();
                                String test = elements.html();
                                productprice.setText(test);


                                try {
                                    Element reviewer = doc.select("span.a-size-base.s-underline-text").first();
                                    String num = reviewer.html();
                                    String formatted = "(" + num + " Reviews" + ")";
                                    reviewnums.setText(formatted);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    reviewnums.setText("(N/A Reviews)");
                                }

                                Element imagey = doc.select("img.s-image").first();
                                String deezsrc = imagey.attr("src");
                                //setimage("https://www.bibdsl.co.uk/imagegallery2/BDS/201844/9781474611565_1.jpg");








                                //a-size-base s-underline-text













                                //Glide.with(getBaseContext()).load("https://www.bibdsl.co.uk/imagegallery2/BDS/201844/9781474611565_1.jpg").into(displayimage);


                                //Glide.with(getApplicationContext()).load("https://www.bibdsl.co.uk/imagegallery2/BDS/201844/9781474611565_1.jpg").into(displayimage);








                                //String linkText = results.text();



                            } catch (IOException e) {
                                e.printStackTrace();

                            }


                        }






                        @Override
                        public void run() {







                        }
                    });
                    //Thread.sleep(30);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

        //Glide.with(this).load(lesrc[0]).into(displayimage);






    }




}