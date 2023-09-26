import {
  Box,
  Card,
  CardContent,
  CardMedia,
  Grid,
  Typography,
} from "@mui/material";
import { AWS_S3_BASE_URL } from "../../config/config";

const responsiveImages = (imageName, productId) => {
  const imageBaseName = imageName.substring(0, imageName.lastIndexOf("."));
  const extension = imageName.substring(imageName.lastIndexOf(".") + 1);

  return (
    <img
      width="150"
      src={`${AWS_S3_BASE_URL}/products/${productId}/${imageBaseName}-large.${extension}`}
      srcSet={`
          ${AWS_S3_BASE_URL}/products/${productId}/${imageBaseName}-small.${extension} 300w, 
          ${AWS_S3_BASE_URL}/products/${productId}/${imageBaseName}-medium.${extension} 700w, 
          ${AWS_S3_BASE_URL}/products/${productId}/${imageBaseName}-large.${extension} 1200w`}
      sizes="(max-width: 300px) 300px, (max-width: 700px) 700px, 1200px"
    />
  );
};

const ProductCard = ({ product }) => {
  return (
    <Grid item sx={{ maxWidth: 200 }}>
      <Box
        sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}
      >
        {product?.images?.map((image) =>
          responsiveImages(image.imageName, product.id)
        )}
      </Box>
      <CardContent>
        <Grid container spacing={1}>
          <Grid item xs={6}>
            <Typography variant="body2" color="textSecondary">
              {product.brand}
            </Typography>
          </Grid>
          <Grid
            item
            xs={6}
            sx={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Typography variant="body2" style={{ color: "green" }}>
              ${product.price}
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography variant="body2">
              {product.description.length > 20
                ? product.description.substring(0, 20) + "..."
                : product.description}
            </Typography>
          </Grid>
        </Grid>
      </CardContent>
    </Grid>
  );
};
export default ProductCard;
