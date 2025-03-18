import { createStore, combineReducers } from "redux";
import {CartReducer} from "./CartReducers";
import {AuthReducer} from "../AuthContainer/AuthReducer";


const rootReducer = combineReducers({
  cart: CartReducer,
  auth: AuthReducer
});

const store = createStore(rootReducer);

export default store;
