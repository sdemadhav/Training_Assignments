import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { purchase_book, selling_book } from '../reduxContainer/BookAction';

function BookContainer() {
  const noOfBooks = useSelector(state => state.NumberOfBooks);
  const dispatch = useDispatch();
  return (
    <div>
        <h2>This is from Book Store Container</h2>
        <h3>No of Books: {noOfBooks}</h3>
        <button onClick={() => dispatch(purchase_book())}>Buy Book</button>
        <button onClick={() => dispatch(selling_book())}>Sell Book</button>
    </div>
  )
}

export default BookContainer