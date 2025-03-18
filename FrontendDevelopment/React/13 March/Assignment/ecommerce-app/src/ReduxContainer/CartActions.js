import { add_to_cart, remove_from_cart } from "./CartActionTypes"

export const addToCart = (product) => {
    return {
        type: add_to_cart,
        payload: product
    }
}

export const removeFromCart = (id , vendorRegId) => {
    return {
        type: remove_from_cart,
        payload: {id, vendorRegId}
    }
}