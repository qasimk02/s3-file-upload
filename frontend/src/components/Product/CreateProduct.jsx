import {
  Button,
  FormControl,
  Grid,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import { useEffect, useState } from "react";
import { Fragment } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addProduct } from "../../state/Product/action";
import CircularProgress from "@mui/material/CircularProgress";
import "./style.css";

const CreateProduct = () => {
  const dispatch = useDispatch();
  const { product } = useSelector((store) => store);

  const productInitalState = {
    title: "",
    brand: "",
    price: "",
    quantity: "",
    category: "",
    description: "",
  };

  const categories = ["shirt", "tshirt", "jeans", "kurta"];
  const [productData, setProductData] = useState({
    title: "",
    brand: "",
    price: "",
    quantity: "",
    category: "",
    description: "",
  });
  const [selectedImage, setSelectedImage] = useState(null);
  const [imagePreview, setImagePreview] = useState(null);

  //handle image change
  const handleImageChange = (event) => {
    const image = event.target.files[0];
    setSelectedImage(image);
    setImagePreview(URL.createObjectURL(image));
  };

  //render selected image
  const renderSelectedImage = (source) => {
    return <img src={source} width={100} />;
  };

  //handle field change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProductData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  //handle form submit
  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(addProduct(productData, selectedImage));
    setProductData(productInitalState);
    setImagePreview(null);
    setSelectedImage(null);
  };

  return (
    <div style={{ position: "relative" }}>
      {product.loading && (
        <div className="overlay">
          <CircularProgress />
        </div>
      )}
      <Fragment>
        <Typography variant="h4" sx={{ textAlign: "center" }} className="py-10">
          Add New Product
        </Typography>
        <form onSubmit={handleSubmit} className="min-h-screen">
          <Grid container spacing={2} sx={{ mb: 2, mt: 2 }}>
            {/* image name */}
            <Grid
              item
              xs={12}
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                flexDirection: "column",
              }}
            >
              <div sx={{ mb: 1 }}>{renderSelectedImage(imagePreview)}</div>
              <input
                type="file"
                accept="image/*"
                onChange={handleImageChange}
              />
            </Grid>
            {/* brand */}
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                required
                label="Brand"
                name="brand"
                value={productData.brand}
                onChange={handleChange}
              />
            </Grid>
            {/* title */}
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                required
                label="Title"
                name="title"
                value={productData.title}
                onChange={handleChange}
              />
            </Grid>
            {/* price */}
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                required
                type="number"
                label="Price"
                name="price"
                value={productData.price}
                onChange={handleChange}
              />
            </Grid>
            {/* price */}
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                required
                type="number"
                label="Quantity"
                name="quantity"
                value={productData.quantity}
                onChange={handleChange}
              />
            </Grid>
            {/* category */}
            <Grid item xs={12} sm={12}>
              <FormControl fullWidth>
                <InputLabel>Select Category</InputLabel>
                <Select
                  required
                  name="category"
                  value={productData.category}
                  onChange={handleChange}
                  label="Select Category"
                >
                  {categories?.map((category, index) => (
                    <MenuItem value={category} key={index}>
                      {category.toUpperCase()}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            {/* description */}
            <Grid item xs={12}>
              <TextField
                fullWidth
                required
                id="outlined-multiline-static"
                label="Description"
                multiline
                name="description"
                rows={3}
                onChange={handleChange}
                value={productData.description}
              />
            </Grid>
            {/* submit button */}
            <Grid
              item
              xs={12}
              sx={{ display: "flex", justifyContent: "center" }}
            >
              <Button
                variant="contained"
                sx={{ p: 1.8 }}
                className="py-20"
                size="large"
                type="submit"
              >
                Add New Product
              </Button>
            </Grid>
          </Grid>
        </form>
      </Fragment>
    </div>
  );
};
export default CreateProduct;
