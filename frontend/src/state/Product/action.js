import { API_BASE_URL } from "../../config/config";
import {
  CREATE_PRODUCTS_FAILURE,
  CREATE_PRODUCTS_REQUEST,
  CREATE_PRODUCTS_SUCCESS,
  GET_ALL_PRODUCTS_FAILURE,
  GET_ALL_PRODUCTS_REQUEST,
  GET_ALL_PRODUCTS_SUCCESS,
} from "./actionType";

import axios from "axios";

const getAllProductsRequest = () => ({ type: GET_ALL_PRODUCTS_REQUEST });
const getAllProductsSuccess = (data) => ({
  type: GET_ALL_PRODUCTS_SUCCESS,
  payload: data,
});
const getAllProductsFailure = (error) => ({
  type: GET_ALL_PRODUCTS_FAILURE,
  payload: error,
});

//create product
const createProductRequest = () => ({ type: CREATE_PRODUCTS_REQUEST });
const createProductSuccess = (data) => ({
  type: CREATE_PRODUCTS_SUCCESS,
  payload: data,
});
const createProductFailure = (error) => ({
  type: CREATE_PRODUCTS_FAILURE,
  payload: error,
});
//get all products
export const getAllProducts = () => async (dispatch) => {
  dispatch(getAllProductsRequest());
  try {
    const { data } = await axios.get(`${API_BASE_URL}/api/products`);
    console.log("all products", data);
    dispatch(getAllProductsSuccess(data));
  } catch (error) {
    dispatch(getAllProductsFailure(error));
  }
};

//create prouduct
export const addProduct = (productData, image) => async (dispatch) => {
  dispatch(createProductRequest());
  try {
    const formData = new FormData();
    formData.append("image", image);
    formData.append("productDetails", JSON.stringify(productData));
    const { data } = await axios.post(`${API_BASE_URL}/api/products`, formData);
    console.log("uploaded", data);
    dispatch(createProductSuccess(data));
  } catch (error) {
    dispatch(createProductFailure(error));
  }
};
