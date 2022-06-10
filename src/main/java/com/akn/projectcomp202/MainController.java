package com.akn.projectcomp202;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import java.io.*;
import java.util.Scanner;

public class MainController extends Username {
    @FXML TableView<Product> table;
    @FXML TableColumn<Product, String> nameColumn;
    @FXML TableColumn<Product, Double> priceColumn;
    @FXML TableColumn<Product, String> quantityColumn;
    @FXML TextField nameField;
    @FXML TextField priceField;
    @FXML TextField quantityField;
    @FXML Button addButton;
    @FXML Button deleteButton;
    @FXML Label errorLabel;

    public void initialize()
    {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        readTxtToProducts();
    }

    public void saveToFile()
    {
        ObservableList<Product> products = table.getItems();

        try {
            FileWriter myWriter = new FileWriter(getUsername() + ".txt");
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(getUsername() + ".txt", true);
            products.forEach((product) -> {
                try {
                    myWriter.write(productToText(product) + "\n");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            });
            myWriter.close();
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public String productToText(Product product)
    {
        return product.getName() + "," + product.getQuantity() + "," + product.getPrice();
    }

    public void readTxtToProducts()
    {
        try {
            File myObj = new File(getUsername() + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] productAr = data.split(",");

                Product product = new Product();

                product.setName(productAr[0]);
                product.setQuantity(Integer.parseInt(productAr[1]));
                product.setPrice(Double.parseDouble(productAr[2]));

                table.getItems().add(product);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void addButtonClicked()
    {
        boolean isInputValid = true;

        try
        {
            Double.parseDouble(priceField.getText());
            Integer.parseInt(quantityField.getText());
        }
        catch (NumberFormatException e)
        {
            isInputValid = false;
        }

        if(isInputValid)
        {
            Product product = new Product();

            product.setName(nameField.getText());
            product.setPrice(Double.parseDouble(priceField.getText()));
            product.setQuantity(Integer.parseInt(quantityField.getText()));

            table.getItems().add(product);

            saveToFile();

            nameField.clear();
            priceField.clear();
            quantityField.clear();

            errorLabel.setTextFill(Paint.valueOf("Green"));
            errorLabel.setText("Item successfully added!");
        }
        else
        {
            errorLabel.setTextFill(Paint.valueOf("Red"));
            errorLabel.setText("One or more input is not valid!");
        }
    }

    public void deleteButtonClicked()
    {
        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);

        saveToFile();
    }

}