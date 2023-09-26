import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getAllProducts } from "../../state/Product/action";
import ProductCard from "../card/ProductCard";
import { Box, Grid } from "@mui/material";

const ProductDetails = () => {
  const dispatch = useDispatch();
  const { product } = useSelector((store) => store);

  useEffect(() => {
    dispatch(getAllProducts());
  }, []);

  return (
    <Grid p={2} container>
      {product?.products?.map((item) => (
        <ProductCard key={item.id} product={item} />
      ))}
    </Grid>
  );
};

export default ProductDetails;
