import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import thunk from "redux-thunk";
import { productReducer } from "./Product/reducer";

const rootReducers = combineReducers({
  product: productReducer,
});

export const store = legacy_createStore(rootReducers, applyMiddleware(thunk));
