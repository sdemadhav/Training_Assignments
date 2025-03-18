import { createStore } from "redux";
import {BookReducer} from "./BookReducers";

const store = createStore(BookReducer)


export default store;