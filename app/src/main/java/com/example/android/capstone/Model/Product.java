package com.example.android.capstone.Model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Product implements Parcelable {

    private String id;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("price_sign")
    @Expose
    private String price_sign;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("image_link")
    @Expose
    private  String image_link;

    @SerializedName("product_link")
    @Expose
    private String product_link;

    @SerializedName("website_link")
    @Expose
    private String website_link;

    @SerializedName("description")
    @Expose
    private String descripition;

    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("product_type")
    @Expose
    private String product_type;

    @SerializedName("tag_list")
    @Expose
    private List<String>  tag_list;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("product_api_url")
    @Expose
    private String product_api_url;

    @SerializedName("api_featured_image")
    @Expose
    private String api_featured_image;

    @SerializedName("product_colors")
    @Expose
    private List<Colors> product_colors;

    public Product(){}

    public Product(String name) {
    }

    public Product(String api_featured_image, String brand, String created_at, String currency, String descripition, String id, String image_link, String name, String price, String price_sign, String product_api_url, String product_link, String product_type, String updated_at, String website_link) {
        this.api_featured_image=api_featured_image;
        this.brand=brand;
        this.created_at=created_at;
        this.currency=currency;
        this.descripition=descripition;
        this.id=id;
        this.image_link=image_link;
        this.name=name;
        this.price=price;
        this.price_sign=price_sign;
        this.product_api_url=product_api_url;
        this.product_link=product_link;
        this.product_type=product_type;
        this.updated_at=updated_at;
        this.website_link=website_link;
    }


    public int getId() {
        return Integer.parseInt(id);
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return Double.valueOf(price);
    }

    public String getPrice_sign() {
        return price_sign;
    }

    public String getCurrency() {
        return currency;
    }

    public String getImage_link() {
        return image_link;
    }

    public String getProduct_link() {
        return product_link;
    }

    public String getWebsite_link() {
        return website_link;
    }

    public String getDescripition() {
        return descripition;
    }

    public String getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct_type() {
        return product_type;
    }

    public List<String> getTag_list() {
        return tag_list;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getProduct_api_url() {
        return product_api_url;
    }

    public String getApi_featured_image() {
        return api_featured_image;
    }

    public List<Colors> getProduct_colors() {
        return product_colors;
    }

    public static Creator<Product> getCREATOR() {
        return CREATOR;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = String.valueOf(price);
    }

    public void setPrice_sign(String price_sign) {
        this.price_sign = price_sign;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public void setProduct_link(String product_link) {
        this.product_link = product_link;
    }

    public void setWebsite_link(String website_link) {
        this.website_link = website_link;
    }

    public void setDescripition(String descripition) {
        this.descripition = descripition;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setTag_list(List<String> tag_list) {
        this.tag_list = tag_list;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setProduct_api_url(String product_api_url) {
        this.product_api_url = product_api_url;
    }

    public void setApi_featured_image(String api_featured_image) {
        this.api_featured_image = api_featured_image;
    }

    public void setProduct_colors(List<Colors> product_colors) {
        this.product_colors = product_colors;
    }

    protected Product(Parcel in) {
        id = String.valueOf(in.readInt());
        brand = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = String.valueOf(in.readDouble());
        }
        price_sign = in.readString();
        currency = in.readString();
        image_link = in.readString();
        product_link = in.readString();
        website_link = in.readString();
        descripition = in.readString();
        rating = in.readString();
        category = in.readString();
        product_type = in.readString();
        tag_list = in.createStringArrayList();
        created_at = in.readString();
        updated_at = in.readString();
        product_api_url = in.readString();
        api_featured_image = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Integer.parseInt(id));
        dest.writeString(brand);
        dest.writeString(name);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Double.parseDouble(price));
        }
        dest.writeString(price_sign);
        dest.writeString(currency);
        dest.writeString(image_link);
        dest.writeString(product_link);
        dest.writeString(website_link);
        dest.writeString(descripition);
        dest.writeString(rating);
        dest.writeString(category);
        dest.writeString(product_type);
        dest.writeStringList(tag_list);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(product_api_url);
        dest.writeString(api_featured_image);
    }
}
