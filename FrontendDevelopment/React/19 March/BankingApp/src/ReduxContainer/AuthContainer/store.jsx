import { combineReducers, createStore } from "redux";
import { AuthReducer } from "./AuthReducer";

export const store = createStore(AuthReducer)