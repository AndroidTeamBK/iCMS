package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sev_user.final_weekone.model.Product;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 14-Sep-16.
 */
public class EditProductActivity extends Activity implements PopupSelectImageColor.EditDialogPopup {

    @Bind(R.id.cre_pro_btn_back1)
    Button cre_pro_BtnBack;
    @Bind(R.id.cre_pro_sku_number1)
    EditText edt_cre_pro_skuNumber;
    @Bind(R.id.cre_pro_name1)
    EditText edt_cre_pro_name;
    @Bind(R.id.cre_pro_quatity1)
    EditText edt_cre_pro_quantity;
    @Bind(R.id.cre_pro_balance1)
    EditText edt_cre_pro_balance;
    @Bind(R.id.cre_pro_spinner_brand1)
    Spinner spn_cre_pro_brand;
    @Bind(R.id.cre_pro_spinner_supplier1)
    Spinner spn_cre_pro_supplier;
    @Bind(R.id.cre_pro_cost1)
    EditText edt_cre_pro_cost;
    @Bind(R.id.cre_pro_unit1)
    EditText edt_cre_pro_unit;
    @Bind(R.id.cre_pro_discounted1)
    EditText edt_cre_pro_discounted;
    @Bind(R.id.cre_pro_size34_1)
    CheckBox checkbox_size34;
    @Bind(R.id.cre_pro_size35_1)
    CheckBox checkbox_size35;
    @Bind(R.id.cre_pro_size36_1)
    CheckBox checkbox_size36;
    @Bind(R.id.cre_pro_radio_gr1)
    RadioGroup radioGr_cre_pro;

    @Bind(R.id.imgcolor1_1)
    ImageView imagColor1;
    @Bind(R.id.imgcolor2_1)
    ImageView imagColor2;
    @Bind(R.id.imgcolor3_1)
    ImageView imagColor3;
    @Bind(R.id.imgcolor4_1)
    ImageView imagColor4;
    @Bind(R.id.imageProduct1_1)
    ImageView imgnewProduct1;

    @Bind(R.id.cre_pro_btn_save1)
    Button btn_cre_pro_create;

    @Bind(R.id.btn_imageProduct1)
    Button addImagenewProduct;

    Product newProduct;
    List<String> list_brandProduct;
    List<String> list_supplierProduct;
    int color[] = new int[4];

    private static final int REQUEST_CAMERA = 0;
    private static final int SELECT_FILE = 1;
    private String userChoosenTask = "";
    Bitmap bm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product_activity);
        ButterKnife.bind(this);
        addSpinerBrandProduct();
        addSpinerSupplierProduct();
        showInformationProduct();
        btn_cre_pro_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_newProduct();
                DataHolderProduct.setProduct(newProduct);
                finish();
            }
        });
    }

    public void showInformationProduct() {
        newProduct = DataHolderProduct.getProduct();
        edt_cre_pro_skuNumber.setText(newProduct.getSkuNumber());
        edt_cre_pro_name.setText(newProduct.getNameProduct());
        edt_cre_pro_quantity.setText(newProduct.getQuantityProduct());
        edt_cre_pro_balance.setText(newProduct.getStockBalance());
        edt_cre_pro_cost.setText("" + newProduct.getPriceProduct());
        edt_cre_pro_unit.setText("" + newProduct.getUnitPriceProduct());
        edt_cre_pro_discounted.setText("" + newProduct.getDiscountPrice());
        imgnewProduct1.setImageBitmap(newProduct.getImageProduct());
    }

    @OnClick(R.id.btnAddColor1)
    public void showPopupSelectColorProduct() {
        FragmentManager fragmentManager = getFragmentManager();
        PopupSelectImageColor popupselectColorProduct = new PopupSelectImageColor();
        popupselectColorProduct.show(fragmentManager, "edit_color_product1");

    }

    @OnClick(R.id.btn_imageProduct1)
    public void selectImageProduct() {
        Log.d("dai.dv", "show........");
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProductActivity.this);
        builder.setTitle("Add Photo for Product");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(EditProductActivity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        //Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imgnewProduct1.setImageBitmap(bm);
    }

    private void onCaptureImageResult(Intent data) {
        bm = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgnewProduct1.setImageBitmap(bm);
    }

    public void create_newProduct() {
        String sku_newProduct = edt_cre_pro_skuNumber.getText().toString();
        String name_newProduct = edt_cre_pro_name.getText().toString();
        String quantity_newProduct = edt_cre_pro_quantity.getText().toString();
        String stockBlance_newProduct = edt_cre_pro_balance.getText().toString();
        String brand_newProduct = String.valueOf(spn_cre_pro_brand.getSelectedItem());
        String supplier_newProduct = String.valueOf(spn_cre_pro_supplier.getSelectedItem());
        String costPrice_newProduct = edt_cre_pro_cost.getText().toString();
        String unitPrice_newProduct = edt_cre_pro_unit.getText().toString();
        String discountPrice_newProduct = edt_cre_pro_discounted.getText().toString();
        String size_newProduct = addSizeProduct();
        if (sku_newProduct.matches("") || name_newProduct.matches("") || quantity_newProduct.matches("") || stockBlance_newProduct.matches(""))
            Toast.makeText(this, "Please fill full information for creating customer", Toast.LENGTH_LONG).show();
        else {
            double costPrice = Double.parseDouble(costPrice_newProduct);
            double unitPrice = Double.parseDouble(unitPrice_newProduct);
            double discountPrice = Double.parseDouble(discountPrice_newProduct);

            newProduct.setSkuNumber(sku_newProduct);
            newProduct.setNameProduct(name_newProduct);
            newProduct.setQuantityProduct(quantity_newProduct);
            newProduct.setStockBalance(stockBlance_newProduct);
            newProduct.setBrandProduct(brand_newProduct);
            newProduct.setSupplier(supplier_newProduct);
            newProduct.setPriceProduct(costPrice);
            newProduct.setUnitPriceProduct(unitPrice);
            newProduct.setDiscountPrice(discountPrice);
            newProduct.setSizeProduct(size_newProduct);
            newProduct.setColorProduct(color);
            newProduct.setImageProduct(bm);

//            Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
//            startActivity(i);
        }

    }

    public void addSpinerBrandProduct() {
        list_brandProduct = new ArrayList<String>();
        list_brandProduct.add("Samsung");
        list_brandProduct.add("Apple");
        list_brandProduct.add("Nokia");
        spn_cre_pro_brand = (Spinner) findViewById(R.id.cre_pro_spinner_brand1);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_brandProduct);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_cre_pro_brand.setAdapter(dataAdapter);
    }

    public void addSpinerSupplierProduct() {
        list_supplierProduct = new ArrayList<String>();
        list_supplierProduct.add("Mr.Dai");
        list_supplierProduct.add("Mr.Toan");
        list_supplierProduct.add("Mr.Robinson");
        spn_cre_pro_supplier = (Spinner) findViewById(R.id.cre_pro_spinner_supplier1);
        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_supplierProduct);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_cre_pro_supplier.setAdapter(dataadapter);
    }

    public String addSizeProduct() {
        String sizeProduct = "";
        if (checkbox_size34.isChecked())
            sizeProduct = "M";
        else if (checkbox_size35.isChecked())
            sizeProduct = "XL";
        else if (checkbox_size36.isChecked())
            sizeProduct = "L";
        return sizeProduct;
    }

    public void updateResult(int[] inputID) {
        color = inputID;
        int countColorSeleciton = color.length;
        Log.d("dai.dv", "" + countColorSeleciton);
        imagColor1.setImageResource(color[0]);
        imagColor2.setImageResource(color[1]);
        imagColor3.setImageResource(color[2]);
        imagColor4.setImageResource(color[3]);
    }

    @OnClick(R.id.cre_pro_btn_back1)
    public void back() {
        finish();
    }
}
