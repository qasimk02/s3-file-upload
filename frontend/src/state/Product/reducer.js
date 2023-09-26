import {
  CREATE_PRODUCTS_FAILURE,
  CREATE_PRODUCTS_REQUEST,
  CREATE_PRODUCTS_SUCCESS,
  GET_ALL_PRODUCTS_FAILURE,
  GET_ALL_PRODUCTS_REQUEST,
  GET_ALL_PRODUCTS_SUCCESS,
} from "./actionType";

const intialState = {
  products: [],
  product: null,
  loading: false,
  error: null,
  deletedProductId: null,
};

export const productReducer = (state = intialState, action) => {
  switch (action.type) {
    //get all products
    case GET_ALL_PRODUCTS_REQUEST:
    case CREATE_PRODUCTS_REQUEST:
      return { ...state, loading: true, error: null };

    case GET_ALL_PRODUCTS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        products: action.payload,
      };
    case CREATE_PRODUCTS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        product: action.payload,
      };

    case GET_ALL_PRODUCTS_FAILURE:
    case CREATE_PRODUCTS_FAILURE:
      return { ...state, loading: false, error: action.payload };

    default:
      return intialState;
  }
};
