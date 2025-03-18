import { add_to_cart, clear_cart, remove_from_cart } from "./CartActionTypes";

const initialState = {
    cartItems: []
}
export const CartReducer = (state=initialState, action) => {
    // console.log("Action: "+ action.type);

    switch(action.type){
        case add_to_cart:
            return {...state, cartItems: [...state.cartItems, action.payload]};
        case remove_from_cart:
            return {...state, cartItems: state.cartItems.filter(item => item.id !== action.payload.id || item.vendor.vendorRegId !== action.payload.vendorRegId)};
        case clear_cart:
            return {...state, cartItems: []};
        default:
            return state
    }
}