# Hướng Dẫn Khi Đề Bài Thay Đổi - Cần Sửa Gì, Ở Đâu?

> Tài liệu dành cho trường hợp đề thi PE thay đổi (tên entity, API, field, validation...).
> Giúp bạn biết chính xác **file nào cần sửa**, **sửa chỗ nào**, và **sửa thế nào**.

---

## Mục Lục

### Phần A: Khi Đề Thay Đổi

1. [Tóm tắt: File nào cần sửa?](#1-tóm-tắt-file-nào-cần-sửa)
2. [Khi đổi API URL](#2-khi-đổi-api-url)
3. [Khi đổi tên Entity (Shop → Product, Book...)](#3-khi-đổi-tên-entity)
4. [Khi đổi các field (cột)](#4-khi-đổi-các-field-cột)
5. [Khi đổi validation (điều kiện nhập liệu)](#5-khi-đổi-validation)
6. [Khi đổi tên dự án / mã đề](#6-khi-đổi-tên-dự-án--mã-đề)
7. [Khi backend có thêm API mới (PUT/PATCH)](#7-khi-backend-có-thêm-api-mới)
8. [Khi đề yêu cầu thêm trang (thêm Route)](#8-khi-đề-yêu-cầu-thêm-trang)

### Phần B: Bảng Tra Cứu Component

9. [Table / Grid](#9-table--grid)
10. [Label](#10-label)
11. [Input TextBox](#11-input-textbox)
12. [Radio Button](#12-radio-button)
13. [CheckBox](#13-checkbox)
14. [DropList (Select)](#14-droplist-select)
15. [Button](#15-button)
16. [Link (Hyperlink)](#16-link-hyperlink)
17. [Modal Popup](#17-modal-popup)
18. [Alert / Notification](#18-alert--notification)
19. [TextArea](#19-textarea)
20. [Date Picker (Input date)](#20-date-picker)
21. [Image (Hiển thị ảnh)](#21-image)

### Phần C: Mẹo Thi

22. [Quy trình làm bài nhanh](#22-quy-trình-làm-bài-nhanh)
23. [Lỗi hay gặp và cách sửa](#23-lỗi-hay-gặp-và-cách-sửa)

---

# PHẦN A: KHI ĐỀ THAY ĐỔI

---

## 1. Tóm tắt: File nào cần sửa?

### Bảng tổng hợp:

| #   | File                           | Khi nào sửa                           | Độ ưu tiên        |
| --- | ------------------------------ | ------------------------------------- | ----------------- |
| 1   | `application.yaml`             | Đổi port backend                      | ⭐⭐⭐            |
| 2   | `src/services/XxxService.js`   | Đổi API URL, thêm/bớt hàm gọi API     | ⭐⭐⭐            |
| 3   | `src/components/XxxList.jsx`   | Đổi field, validation, UI form, table | ⭐⭐⭐            |
| 4   | `src/components/XxxDetail.jsx` | Đổi field hiển thị                    | ⭐⭐⭐            |
| 5   | `src/App.jsx`                  | Đổi route path, thêm route            | ⭐⭐              |
| 6   | `index.html`                   | Đổi title trang                       | ⭐                |
| 7   | `vite.config.js`               | Đổi port frontend                     | ⭐ (hiếm khi đổi) |
| 8   | `main.jsx`                     | **KHÔNG CẦN SỬA** (cấu hình chung)    | —                 |
| 9   | `jsconfig.json`                | **KHÔNG CẦN SỬA**                     | —                 |

### Sơ đồ phụ thuộc:

```
Đọc đề → Xác định Entity + API
                ↓
┌─────────────────────────────────────────────────┐
│  1. Service.js     ← Đổi API_URL + tên hàm     │
│  2. XxxList.jsx    ← Đổi form + table + logic   │
│  3. XxxDetail.jsx  ← Đổi hiển thị chi tiết      │
│  4. App.jsx        ← Đổi route + import          │
│  5. index.html     ← Đổi <title>                 │
└─────────────────────────────────────────────────┘
```

---

## 2. Khi đổi API URL

### Tình huống:

Đề mới có API base khác, ví dụ:

- Cũ: `http://localhost:8080/myapp/shops`
- Mới: `http://localhost:8080/api/products`

### Sửa ở đâu: `src/services/XxxService.js`

**CHỈ CẦN SỬA 1 DÒNG:**

```javascript
// CŨ:
const API_URL = "http://localhost:8080/myapp/shops";

// MỚI:
const API_URL = "http://localhost:8080/api/products";
```

→ Tất cả hàm `getAll`, `getById`, `create`, `deleteXxx` sẽ **tự động dùng URL mới** vì đều tham chiếu `API_URL`.

### Nếu đề đổi endpoint phụ:

```javascript
// CŨ: Lấy types
const getTypes = () => axios.get(`${API_URL}/types`);

// MỚI: Đề gọi /categories thay vì /types
const getCategories = () => axios.get(`${API_URL}/categories`);
```

→ Đổi tên hàm + đường dẫn, rồi cập nhật ở component gọi hàm đó.

---

## 3. Khi đổi tên Entity

### Tình huống:

Đề không phải "Shop" mà là "Product", "Book", "Student", "Employee"...

### Phải sửa ở các file sau:

#### 3.1 Đổi tên file (không bắt buộc nhưng nên làm):

| Cũ                          | Mới (ví dụ Product)            |
| --------------------------- | ------------------------------ |
| `services/ShopService.js`   | `services/ProductService.js`   |
| `components/ShopList.jsx`   | `components/ProductList.jsx`   |
| `components/ShopDetail.jsx` | `components/ProductDetail.jsx` |

#### 3.2 Sửa `src/services/ProductService.js`:

```javascript
import axios from "axios";

const API_URL = "http://localhost:8080/api/products"; // ← URL mới

const getAll = () => axios.get(API_URL);
const getById = (id) => axios.get(`${API_URL}/${id}`);
const create = (product) => axios.post(API_URL, product); // ← đổi tên param
const deleteProduct = (id) => axios.delete(`${API_URL}/${id}`); // ← đổi tên hàm
const getCategories = () => axios.get(`${API_URL}/categories`); // ← endpoint phụ

export default { getAll, getById, create, deleteProduct, getCategories };
```

#### 3.3 Sửa `src/App.jsx`:

```jsx
import { Routes, Route } from "react-router-dom";
import ProductList from "./components/ProductList"; // ← đổi import
import ProductDetail from "./components/ProductDetail"; // ← đổi import

function App() {
  return (
    <Routes>
      <Route path="/" element={<ProductList />} /> {/* ← đổi component */}
      <Route path="/product/:id" element={<ProductDetail />} />{" "}
      {/* ← đổi path */}
    </Routes>
  );
}

export default App;
```

#### 3.4 Sửa `index.html`:

```html
<title>Product Management</title>
<!-- ← đổi title -->
```

#### 3.5 Sửa component List và Detail: (xem mục 4)

---

## 4. Khi đổi các field (cột)

### Tình huống:

Entity mới có field khác. Ví dụ:

| Shop (cũ)      | Product (mới)                  |
| -------------- | ------------------------------ |
| name (String)  | productName (String)           |
| openTime (int) | price (double)                 |
| owner (String) | brand (String)                 |
| type (String)  | category (String)              |
| —              | inStock (boolean) — **MỚI**    |
| —              | description (String) — **MỚI** |

### Sửa ở `XxxList.jsx` - Có 4 vùng cần sửa:

---

#### VÙNG 1: State variables

```jsx
// CŨ (Shop):
const [name, setName] = useState("");
const [openTime, setOpenTime] = useState("");
const [owner, setOwner] = useState("");
const [type, setType] = useState("");

// MỚI (Product):
const [productName, setProductName] = useState("");
const [price, setPrice] = useState("");
const [brand, setBrand] = useState("");
const [category, setCategory] = useState("");
const [inStock, setInStock] = useState(false); // boolean → false
const [description, setDescription] = useState(""); // textarea
```

**Quy tắc đặt giá trị khởi tạo:**

| Loại field            | Giá trị khởi tạo | Ví dụ             |
| --------------------- | ---------------- | ----------------- |
| String (text input)   | `''`             | `useState('')`    |
| Number (number input) | `''` hoặc `0`    | `useState('')`    |
| Boolean (checkbox)    | `false`          | `useState(false)` |
| Select/Dropdown       | `''`             | `useState('')`    |
| Date                  | `''`             | `useState('')`    |
| Array                 | `[]`             | `useState([])`    |

---

#### VÙNG 2: Form (phần nhập liệu)

Sửa các `<Form.Group>` cho khớp field mới. Xem [Phần B](#9-table--grid) để biết cách viết từng loại component.

```jsx
{
  /* Ví dụ thêm field price (Number) */
}
<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">
    Price:
  </Form.Label>
  <Col sm={10}>
    <Form.Control
      type="number"
      value={price}
      onChange={(e) => setPrice(e.target.value)}
      min={0}
    />
  </Col>
</Form.Group>;

{
  /* Ví dụ thêm field inStock (Checkbox) */
}
<Form.Group as={Row} className="mb-2">
  <Col sm={{ span: 10, offset: 2 }}>
    <Form.Check
      type="checkbox"
      label="In Stock"
      checked={inStock}
      onChange={(e) => setInStock(e.target.checked)}
    />
  </Col>
</Form.Group>;

{
  /* Ví dụ thêm field description (TextArea) */
}
<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">
    Description:
  </Form.Label>
  <Col sm={10}>
    <Form.Control
      as="textarea"
      rows={3}
      value={description}
      onChange={(e) => setDescription(e.target.value)}
    />
  </Col>
</Form.Group>;
```

---

#### VÙNG 3: Object gửi lên API (trong handleAddNew)

```jsx
// CŨ:
const shop = {
  name: name.trim(),
  openTime: openTimeNum,
  owner: owner.trim(),
  type,
};

// MỚI:
const product = {
  productName: productName.trim(),
  price: Number(price),
  brand: brand.trim(),
  category,
  inStock,
  description: description.trim(),
};

ProductService.create(product).then(() => {
  alert("Created new Product successfully");
  loadProducts();
  // Reset form
  setProductName("");
  setPrice("");
  setBrand("");
  setCategory("");
  setInStock(false);
  setDescription("");
});
```

**QUAN TRỌNG**: Tên thuộc tính trong object **PHẢI KHỚP** với tên field trong Entity Java ở backend.

```
Java Entity:              JavaScript Object:
private String productName;  →  productName: "..."
private double price;        →  price: 123.45
private boolean inStock;     →  inStock: true
```

---

#### VÙNG 4: Table (bảng hiển thị)

```jsx
{
  /* Sửa thead */
}
<thead>
  <tr>
    <th># No</th>
    <th>Product Name</th> {/* ← đổi */}
    <th>Category</th> {/* ← đổi */}
    <th>Brand</th> {/* ← đổi */}
    <th>Price</th> {/* ← đổi/thêm */}
    <th>In Stock</th> {/* ← thêm mới */}
    <th>Action</th>
  </tr>
</thead>;

{
  /* Sửa tbody */
}
<tbody>
  {products.map((product, index) => (
    <tr key={product.id}>
      <td>{String(index + 1).padStart(2, "0")}</td>
      <td>{product.productName}</td> {/* ← đổi */}
      <td>{product.category}</td> {/* ← đổi */}
      <td>{product.brand}</td> {/* ← đổi */}
      <td>{product.price}</td> {/* ← đổi */}
      <td>{product.inStock ? "Yes" : "No"}</td> {/* ← boolean */}
      <td>
        <Button
          variant="danger"
          size="sm"
          onClick={() => handleDeleteClick(product)}
        >
          Delete
        </Button>
        {" | "}
        <Link to={`/product/${product.id}`}>View</Link>
      </td>
    </tr>
  ))}
</tbody>;
```

---

### Sửa ở `XxxDetail.jsx`:

```jsx
function ProductDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    ProductService.getById(id).then((res) => setProduct(res.data));
  }, [id]);

  if (!product) return <Container className="mt-3">Loading...</Container>;

  return (
    <Container className="mt-3">
      <h2>
        <b>VIEW DETAILS</b>
      </h2>
      <div className="mt-4 ms-4">
        <p>
          <b>Product Name:</b> {product.productName}
        </p>{" "}
        {/* ← đổi field */}
        <p>
          <b>Brand:</b> {product.brand}
        </p>{" "}
        {/* ← đổi field */}
        <p>
          <b>Category:</b> {product.category}
        </p>{" "}
        {/* ← đổi field */}
        <p>
          <b>Price:</b> {product.price}
        </p>{" "}
        {/* ← đổi field */}
        <p>
          <b>In Stock:</b> {product.inStock ? "Yes" : "No"}
        </p>{" "}
        {/* ← boolean */}
        <p>
          <b>Description:</b> {product.description}
        </p>{" "}
        {/* ← thêm field */}
      </div>
      <Button variant="outline-primary" onClick={() => navigate("/")}>
        Quay lai
      </Button>
    </Container>
  );
}
```

---

## 5. Khi đổi validation

### Tình huống:

Đề mới có điều kiện validation khác.

### Sửa ở: `XxxList.jsx` → hàm `handleAddNew`

### Đọc validation từ backend Entity:

Xem file Java Entity để biết validation:

```java
// Ví dụ Entity Java:
@Column(nullable = false, length = 50, unique = true)
private String productName;     // → required, max 50 ký tự, không trùng

@Column(nullable = false)
private double price;            // → required

@Column(nullable = false, length = 100)
private String brand;            // → required, max 100 ký tự
```

### Chuyển thành validation JavaScript:

```jsx
const handleAddNew = () => {
  // === VALIDATION ===

  // String - required + maxLength
  if (!productName.trim()) { alert('Product name is required'); return }
  if (productName.trim().length > 50) { alert('Product name must be at most 50 characters'); return }

  // Number - khoảng giá trị
  if (!price) { alert('Price is required'); return }
  const priceNum = Number(price)
  if (isNaN(priceNum) || priceNum <= 0) {
    alert('Price must be greater than 0'); return
  }

  // String - required + maxLength
  if (!brand.trim()) { alert('Brand is required'); return }
  if (brand.trim().length > 100) { alert('Brand must be at most 100 characters'); return }

  // Select - required
  if (!category) { alert('Category is required'); return }

  // === TẠO OBJECT ===
  const product = { ... }
  // ...
}
```

### Bảng chuyển đổi validation phổ biến:

| Điều kiện đề bài               | Code JavaScript                                             |
| ------------------------------ | ----------------------------------------------------------- |
| Required (bắt buộc nhập)       | `if (!value.trim()) { alert('...'); return }`               |
| Max length (tối đa N ký tự)    | `if (value.trim().length > N) { alert('...'); return }`     |
| Min length (tối thiểu N ký tự) | `if (value.trim().length < N) { alert('...'); return }`     |
| Giá trị > 0                    | `if (Number(value) <= 0) { alert('...'); return }`          |
| Giá trị trong khoảng [a, b]    | `if (num < a \|\| num > b) { alert('...'); return }`        |
| Giá trị > a VÀ < b             | `if (num <= a \|\| num >= b) { alert('...'); return }`      |
| Phải là số                     | `if (isNaN(Number(value))) { alert('...'); return }`        |
| Select bắt buộc                | `if (!selectValue) { alert('...'); return }`                |
| Checkbox bắt buộc              | `if (!checked) { alert('...'); return }`                    |
| Email format                   | `if (!/\S+@\S+\.\S+/.test(value)) { alert('...'); return }` |
| Chỉ chứa chữ cái               | `if (!/^[a-zA-Z ]+$/.test(value)) { alert('...'); return }` |

### Đọc validation từ backend Service:

Xem file `ShopService.java` (hoặc tương đương) để biết thêm:

```java
// Ví dụ:
if (shopRepository.existsByName(shop.getName())) {
    throw new RuntimeException("Name already exists");
}
```

→ Backend sẽ trả lỗi nếu trùng tên. Frontend catch trong `.catch()`:

```javascript
.catch((err) => {
  alert(err.response?.data?.message || 'Error creating product')
})
```

---

## 6. Khi đổi tên dự án / mã đề

### Tình huống:

- Mã đề mới: `PRM` (thay vì `BSM`)
- Tên dự án: `Product Management` (thay vì `Book Shop Management`)

### Sửa ở:

| Vị trí                   | Cũ                       | Mới                              |
| ------------------------ | ------------------------ | -------------------------------- |
| Tên thư mục frontend     | `SE1805_SE180211_BSM_PE` | `SE1805_SE180211_PRM_PE`         |
| `index.html` → `<title>` | `Book Shop Management`   | `Product Management`             |
| `XxxList.jsx` → `<h2>`   | `Book Shop Management`   | `Product Management`             |
| `XxxList.jsx` → `<h4>`   | `Shop List`              | `Product List`                   |
| `XxxDetail.jsx` → `<h2>` | `VIEW DETAILS`           | (thường giữ nguyên hoặc theo đề) |

### Lệnh tạo dự án:

```bash
npm create vite@latest SE1805_SE180211_PRM_PE -- --template react
```

---

## 7. Khi backend có thêm API mới

### Tình huống: Đề có thêm PUT (update)

#### 7.1 Thêm hàm trong Service:

```javascript
// src/services/ProductService.js
const update = (id, product) => axios.put(`${API_URL}/${id}`, product);

export default {
  getAll,
  getById,
  create,
  update,
  deleteProduct,
  getCategories,
};
//                                         ↑ thêm
```

#### 7.2 Thêm logic trong List component:

```jsx
// State thêm: đang edit hay add?
const [editId, setEditId] = useState(null); // null = add mode, có id = edit mode

const handleSubmit = () => {
  // ... validation ...

  const product = { productName, price: Number(price), brand, category };

  if (editId) {
    // MODE: UPDATE
    ProductService.update(editId, product)
      .then(() => {
        alert("Updated successfully");
        loadProducts();
        resetForm();
        setEditId(null);
      })
      .catch((err) => alert(err.response?.data?.message || "Error"));
  } else {
    // MODE: CREATE
    ProductService.create(product)
      .then(() => {
        alert("Created successfully");
        loadProducts();
        resetForm();
      })
      .catch((err) => alert(err.response?.data?.message || "Error"));
  }
};

// Khi click Edit trên table:
const handleEditClick = (product) => {
  setEditId(product.id);
  setProductName(product.productName);
  setPrice(String(product.price));
  setBrand(product.brand);
  setCategory(product.category);
};

// Reset form:
const resetForm = () => {
  setProductName("");
  setPrice("");
  setBrand("");
  setCategory("");
  setEditId(null);
};
```

#### 7.3 Thêm nút Edit trong table:

```jsx
<td>
  <Button variant="warning" size="sm" onClick={() => handleEditClick(product)}>
    Edit
  </Button>
  {" | "}
  <Button variant="danger" size="sm" onClick={() => handleDeleteClick(product)}>
    Delete
  </Button>
  {" | "}
  <Link to={`/product/${product.id}`}>View</Link>
</td>
```

#### 7.4 Đổi text nút submit:

```jsx
<Button variant="primary" onClick={handleSubmit}>
  {editId ? "Update" : "Add New"}
</Button>;
{
  editId && (
    <Button variant="secondary" className="ms-2" onClick={resetForm}>
      Cancel
    </Button>
  );
}
```

---

## 8. Khi đề yêu cầu thêm trang

### Tình huống: Đề có 3 trang (thêm trang Edit riêng)

#### 8.1 Thêm Route trong `App.jsx`:

```jsx
import ProductEdit from "./components/ProductEdit"; // ← thêm import

<Routes>
  <Route path="/" element={<ProductList />} />
  <Route path="/product/:id" element={<ProductDetail />} />
  <Route path="/product/edit/:id" element={<ProductEdit />} />{" "}
  {/* ← thêm route */}
</Routes>;
```

#### 8.2 Tạo component `ProductEdit.jsx`:

```jsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Form, Button, Row, Col } from "react-bootstrap";
import ProductService from "../services/ProductService";

function ProductEdit() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [productName, setProductName] = useState("");
  const [price, setPrice] = useState("");
  // ... các state khác

  useEffect(() => {
    ProductService.getById(id).then((res) => {
      const p = res.data;
      setProductName(p.productName);
      setPrice(String(p.price));
      // ... set các state khác
    });
  }, [id]);

  const handleUpdate = () => {
    // validation...
    const product = { productName, price: Number(price) };
    ProductService.update(id, product)
      .then(() => {
        alert("Updated successfully");
        navigate("/");
      })
      .catch((err) => alert(err.response?.data?.message || "Error"));
  };

  return (
    <Container className="mt-3">
      <h2>
        <b>Edit Product</b>
      </h2>
      <Form>
        {/* ... form giống List nhưng có dữ liệu sẵn */}
        <Button onClick={handleUpdate}>Update</Button>
        <Button
          variant="secondary"
          className="ms-2"
          onClick={() => navigate("/")}
        >
          Cancel
        </Button>
      </Form>
    </Container>
  );
}

export default ProductEdit;
```

#### 8.3 Thêm link Edit trong table (List):

```jsx
<Link to={`/product/edit/${product.id}`}>Edit</Link>
```

---

# PHẦN B: BẢNG TRA CỨU COMPONENT

> Copy-paste trực tiếp vào code. Chỉ cần đổi tên biến và value cho phù hợp đề bài.

---

## 9. Table / Grid

### Screen Definition ghi: **Grid** hoặc **Table**

```jsx
import { Table } from "react-bootstrap";

<Table bordered hover>
  <thead>
    <tr>
      <th># No</th>
      <th>Product Name</th>
      <th>Price</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    {products.map((product, index) => (
      <tr key={product.id}>
        <td>{String(index + 1).padStart(2, "0")}</td>
        <td>{product.productName}</td>
        <td>{product.price}</td>
        <td>{/* Các nút action */}</td>
      </tr>
    ))}
  </tbody>
</Table>;
```

### Props của Table:

| Prop        | Ý nghĩa                     |
| ----------- | --------------------------- |
| `bordered`  | Có viền                     |
| `hover`     | Highlight dòng khi rê chuột |
| `striped`   | Dòng chẵn/lẻ màu khác nhau  |
| `size="sm"` | Table nhỏ gọn hơn           |

### Hiển thị số thứ tự có đệm 0:

```jsx
{
  String(index + 1).padStart(2, "0");
}
// index=0 → "01", index=1 → "02", ..., index=9 → "10"
```

### Khi table trống:

```jsx
<tbody>
  {products.length === 0 ? (
    <tr>
      <td colSpan={4} className="text-center">
        No data found
      </td>
    </tr>
  ) : (
    products.map((product, index) => <tr key={product.id}>...</tr>)
  )}
</tbody>
```

### Hiển thị boolean trong table:

```jsx
<td>{product.inStock ? 'Yes' : 'No'}</td>
// hoặc
<td>{product.inStock ? '✓' : '✗'}</td>
```

---

## 10. Label

### Screen Definition ghi: **Label**

```jsx
import { Form, Row, Col } from 'react-bootstrap'

{/* Label nằm cùng dòng với input (horizontal form) */}
<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">Product Name:</Form.Label>
  <Col sm={10}>
    <Form.Control ... />
  </Col>
</Form.Group>
```

| Prop                   | Ý nghĩa                                    |
| ---------------------- | ------------------------------------------ |
| `column`               | Canh label thẳng hàng với input (theo dọc) |
| `sm={2}`               | Chiếm 2/12 cột (≈ 16.7% chiều rộng)        |
| `className="text-end"` | Căn chữ sang phải (sát input)              |

### Label đơn giản (không horizontal):

```jsx
<Form.Group className="mb-2">
  <Form.Label>Product Name:</Form.Label>
  <Form.Control ... />
</Form.Group>
```

### Label hiển thị text (không phải form):

```jsx
<p>
  <b>Product Name:</b> {product.productName}
</p>
```

---

## 11. Input TextBox

### Screen Definition ghi: **TextBox** hoặc **Input**

### Text Input:

```jsx
<Form.Control
  type="text"
  value={productName}
  onChange={(e) => setProductName(e.target.value)}
  placeholder="Enter product name"
  maxLength={50}
/>
```

### Number Input:

```jsx
<Form.Control
  type="number"
  value={price}
  onChange={(e) => setPrice(e.target.value)}
  min={0}
  max={99999}
  step={0.01}
/>
```

### Password Input:

```jsx
<Form.Control
  type="password"
  value={password}
  onChange={(e) => setPassword(e.target.value)}
/>
```

### Email Input:

```jsx
<Form.Control
  type="email"
  value={email}
  onChange={(e) => setEmail(e.target.value)}
  placeholder="example@email.com"
/>
```

### Disabled / ReadOnly:

```jsx
<Form.Control type="text" value={value} disabled />        {/* Xám, không sửa được */}
<Form.Control type="text" value={value} readOnly />        {/* Trắng, không sửa được */}
```

### Props phổ biến:

| Prop          | Ý nghĩa                  | Ví dụ                                                   |
| ------------- | ------------------------ | ------------------------------------------------------- |
| `type`        | Loại input               | `"text"`, `"number"`, `"email"`, `"password"`, `"date"` |
| `value`       | Giá trị (controlled)     | `value={name}`                                          |
| `onChange`    | Hàm gọi khi gõ           | `onChange={(e) => setName(e.target.value)}`             |
| `placeholder` | Gợi ý khi trống          | `placeholder="Enter name"`                              |
| `maxLength`   | Số ký tự tối đa          | `maxLength={50}`                                        |
| `min`, `max`  | Giá trị min/max (number) | `min={0} max={100}`                                     |
| `step`        | Bước nhảy (number)       | `step={0.5}`                                            |
| `disabled`    | Vô hiệu hóa              | `disabled`                                              |
| `readOnly`    | Chỉ đọc                  | `readOnly`                                              |

---

## 12. Radio Button

### Screen Definition ghi: **Radio Button**

### Cách 1: Radio đơn giản

```jsx
const [gender, setGender] = useState('')

<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">Gender:</Form.Label>
  <Col sm={10}>
    <Form.Check
      inline                              // Nằm ngang
      type="radio"
      label="Male"
      name="gender"                       // Cùng name → cùng nhóm
      value="Male"
      checked={gender === 'Male'}         // Chọn khi state = "Male"
      onChange={(e) => setGender(e.target.value)}
    />
    <Form.Check
      inline
      type="radio"
      label="Female"
      name="gender"
      value="Female"
      checked={gender === 'Female'}
      onChange={(e) => setGender(e.target.value)}
    />
  </Col>
</Form.Group>
```

### Cách 2: Radio từ mảng (dynamic)

```jsx
const [status, setStatus] = useState('')
const statusOptions = ['Active', 'Inactive', 'Pending']

<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">Status:</Form.Label>
  <Col sm={10}>
    {statusOptions.map((opt) => (
      <Form.Check
        key={opt}
        inline
        type="radio"
        label={opt}
        name="status"
        value={opt}
        checked={status === opt}
        onChange={(e) => setStatus(e.target.value)}
      />
    ))}
  </Col>
</Form.Group>
```

### Props Radio:

| Prop           | Ý nghĩa                                      |
| -------------- | -------------------------------------------- |
| `type="radio"` | Kiểu radio button                            |
| `name`         | Tên nhóm (cùng name = cùng nhóm, chỉ chọn 1) |
| `label`        | Text hiển thị                                |
| `value`        | Giá trị khi chọn                             |
| `checked`      | Có đang được chọn không (so sánh với state)  |
| `onChange`     | Hàm gọi khi chọn                             |
| `inline`       | Nằm ngang (không có thì xuống dòng)          |

---

## 13. CheckBox

### Screen Definition ghi: **CheckBox**

### CheckBox đơn (true/false):

```jsx
const [inStock, setInStock] = useState(false)

<Form.Group as={Row} className="mb-2">
  <Col sm={{ span: 10, offset: 2 }}>
    <Form.Check
      type="checkbox"
      label="In Stock"
      checked={inStock}                              // boolean
      onChange={(e) => setInStock(e.target.checked)}  // .checked (KHÔNG PHẢI .value)
    />
  </Col>
</Form.Group>
```

### Nhiều checkbox (chọn nhiều):

```jsx
const [skills, setSkills] = useState([]);
const allSkills = ["React", "Angular", "Vue", "Node.js"];

const handleSkillChange = (skill) => {
  if (skills.includes(skill)) {
    setSkills(skills.filter((s) => s !== skill)); // Bỏ chọn → xóa khỏi mảng
  } else {
    setSkills([...skills, skill]); // Chọn → thêm vào mảng
  }
};

<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">
    Skills:
  </Form.Label>
  <Col sm={10}>
    {allSkills.map((skill) => (
      <Form.Check
        key={skill}
        inline
        type="checkbox"
        label={skill}
        checked={skills.includes(skill)}
        onChange={() => handleSkillChange(skill)}
      />
    ))}
  </Col>
</Form.Group>;
```

### Chú ý quan trọng:

```
Radio:     onChange={(e) => setState(e.target.value)}    ← dùng .value
Checkbox:  onChange={(e) => setState(e.target.checked)}  ← dùng .checked
```

---

## 14. DropList (Select)

### Screen Definition ghi: **DropList** hoặc **DropDownList** hoặc **ComboBox**

### Select cơ bản (danh sách cố định):

```jsx
const [category, setCategory] = useState('')

<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">Category:</Form.Label>
  <Col sm={10}>
    <Form.Select value={category} onChange={(e) => setCategory(e.target.value)}>
      <option value="">-- Select --</option>
      <option value="Electronics">Electronics</option>
      <option value="Clothing">Clothing</option>
      <option value="Food">Food</option>
    </Form.Select>
  </Col>
</Form.Group>
```

### Select dynamic (từ API):

```jsx
const [types, setTypes] = useState([])
const [type, setType] = useState('')

useEffect(() => {
  ProductService.getCategories().then((res) => setTypes(res.data))
}, [])

<Form.Select value={type} onChange={(e) => setType(e.target.value)}>
  <option value="">-- Select --</option>
  {types.map((t) => (
    <option key={t} value={t}>{t}</option>
  ))}
</Form.Select>
```

### Select khi API trả về object:

```jsx
// API trả về: [{ id: 1, name: "Electronics" }, { id: 2, name: "Clothing" }]
const [categories, setCategories] = useState([])
const [categoryId, setCategoryId] = useState('')

<Form.Select value={categoryId} onChange={(e) => setCategoryId(e.target.value)}>
  <option value="">-- Select --</option>
  {categories.map((cat) => (
    <option key={cat.id} value={cat.id}>{cat.name}</option>
    {/*                   ↑ value = id      ↑ hiển thị = name */}
  ))}
</Form.Select>
```

### Lưu ý:

- `<option value="">-- Select --</option>` → option mặc định (chưa chọn)
- Validation: `if (!type) { alert('Type is required'); return }` → kiểm tra `value=""` (rỗng)
- `<Form.Select>` dùng cho dropdown, **KHÔNG** dùng `<Form.Control as="select">`

---

## 15. Button

### Screen Definition ghi: **Button**

### Các loại Button:

```jsx
import { Button } from "react-bootstrap";

{
  /* Button thường */
}
<Button variant="primary" onClick={handleAddNew}>
  Add New
</Button>;

{
  /* Button xóa */
}
<Button variant="danger" size="sm" onClick={() => handleDeleteClick(item)}>
  Delete
</Button>;

{
  /* Button submit form */
}
<Button type="submit" variant="primary">
  Submit
</Button>;

{
  /* Button outline (chỉ viền) */
}
<Button variant="outline-primary" onClick={() => navigate("/")}>
  Back
</Button>;

{
  /* Button disabled */
}
<Button variant="primary" disabled={loading}>
  {loading ? "Saving..." : "Save"}
</Button>;
```

### Tất cả variant:

| Variant           | Màu           | Dùng khi                                 |
| ----------------- | ------------- | ---------------------------------------- |
| `primary`         | Xanh dương    | Hành động chính (Add, Save, Submit, Yes) |
| `secondary`       | Xám           | Hành động phụ (Cancel, Close, No)        |
| `success`         | Xanh lá       | Thành công (Approve, Confirm)            |
| `danger`          | Đỏ            | Nguy hiểm (Delete, Remove)               |
| `warning`         | Vàng          | Cảnh báo (Edit, Warn)                    |
| `info`            | Xanh nhạt     | Thông tin                                |
| `link`            | Như hyperlink | Trông như link                           |
| `outline-primary` | Viền xanh     | Button nhẹ nhàng (Back, Cancel)          |
| `outline-danger`  | Viền đỏ       | Delete nhẹ nhàng                         |

### Props Button:

| Prop        | Ý nghĩa               | Ví dụ                                  |
| ----------- | --------------------- | -------------------------------------- |
| `variant`   | Kiểu màu              | `"primary"`, `"danger"`                |
| `size`      | Kích thước            | `"sm"`, `"lg"`                         |
| `onClick`   | Hàm khi click         | `onClick={handleClick}`                |
| `type`      | Loại nút (trong form) | `"submit"`, `"button"`                 |
| `disabled`  | Vô hiệu hóa           | `disabled` hoặc `disabled={condition}` |
| `className` | CSS class             | `className="ms-2"`                     |

---

## 16. Link (Hyperlink)

### Screen Definition ghi: **Hyperlink** hoặc **Link**

### Link chuyển trang:

```jsx
import { Link } from "react-router-dom";

{
  /* Link cơ bản */
}
<Link to={`/product/${product.id}`}>View</Link>;

{
  /* Link về trang chủ */
}
<Link to="/">Back to List</Link>;

{
  /* Link với className */
}
<Link to={`/product/${product.id}`} className="text-decoration-none">
  View Details
</Link>;
```

### Link vs Button (cách phân biệt):

Xem **Screen Definition** trong đề bài:

| Screen Definition ghi | Dùng gì                  |
| --------------------- | ------------------------ |
| Type: **Hyperlink**   | `<Link to="...">`        |
| Type: **Button**      | `<Button onClick={...}>` |
| Type: **Link**        | `<Link to="...">`        |

```
Screen Definition Example:
┌───────────┬──────────┬───────────┐
│ Name      │ Type     │ Mapping   │
├───────────┼──────────┼───────────┤
│ View      │ Hyperlink│ /item/:id │   → dùng <Link>
│ Delete    │ Button   │ -         │   → dùng <Button>
│ Add New   │ Button   │ -         │   → dùng <Button>
└───────────┴──────────┴───────────┘
```

### Chuyển trang bằng code (không phải link):

```jsx
import { useNavigate } from "react-router-dom";

const navigate = useNavigate();

// Chuyển đến trang khác
navigate("/product/5");

// Quay lại trang trước
navigate(-1);

// Quay về trang chủ
navigate("/");
```

---

## 17. Modal Popup

### Screen Definition ghi: **Modal** hoặc **Dialog** hoặc **Popup** hoặc **Confirmation**

### Modal xác nhận xóa (hay dùng nhất):

```jsx
import { Modal, Button } from "react-bootstrap";

// State
const [showConfirm, setShowConfirm] = useState(false);
const [deleteTarget, setDeleteTarget] = useState(null);

// Mở modal
const handleDeleteClick = (item) => {
  setDeleteTarget(item);
  setShowConfirm(true);
};

// Xóa thật
const handleDeleteConfirm = () => {
  ProductService.deleteProduct(deleteTarget.id).then(() => {
    alert("Deleted successfully");
    loadProducts();
    setShowConfirm(false);
    setDeleteTarget(null);
  });
};

// Đóng modal (hủy xóa)
const handleDeleteClose = () => {
  setShowConfirm(false);
  setDeleteTarget(null);
};

// JSX
<Modal show={showConfirm} onHide={handleDeleteClose} centered>
  <Modal.Header closeButton>
    <Modal.Title>Confirmation</Modal.Title>
  </Modal.Header>
  <Modal.Body>
    Are you sure you want to delete "{deleteTarget?.name}"?
  </Modal.Body>
  <Modal.Footer>
    <Button variant="primary" onClick={handleDeleteConfirm}>
      Yes
    </Button>
    <Button variant="secondary" onClick={handleDeleteClose}>
      Close
    </Button>
  </Modal.Footer>
</Modal>;
```

### Props Modal:

| Prop                | Ý nghĩa                                  |
| ------------------- | ---------------------------------------- |
| `show`              | `true` = hiện, `false` = ẩn              |
| `onHide`            | Hàm gọi khi click ngoài modal hoặc nút X |
| `centered`          | Canh giữa màn hình                       |
| `size="lg"`         | Modal lớn                                |
| `size="sm"`         | Modal nhỏ                                |
| `backdrop="static"` | KHÔNG đóng khi click ngoài               |

### Modal hiển thị thông tin (không phải xóa):

```jsx
<Modal show={showInfo} onHide={() => setShowInfo(false)} centered>
  <Modal.Header closeButton>
    <Modal.Title>Product Details</Modal.Title>
  </Modal.Header>
  <Modal.Body>
    <p>
      <b>Name:</b> {selectedProduct?.name}
    </p>
    <p>
      <b>Price:</b> {selectedProduct?.price}
    </p>
  </Modal.Body>
  <Modal.Footer>
    <Button variant="secondary" onClick={() => setShowInfo(false)}>
      Close
    </Button>
  </Modal.Footer>
</Modal>
```

### Access operator `?.` (Optional Chaining):

```jsx
deleteTarget?.name;
// Nếu deleteTarget = null → trả về undefined (KHÔNG lỗi)
// Nếu deleteTarget = { name: "ABC" } → trả về "ABC"
```

---

## 18. Alert / Notification

### Dùng `alert()` (phổ biến trong PE):

```jsx
alert("Created new Product successfully"); // Thông báo thành công
alert("Price must be greater than 0"); // Thông báo lỗi validation
alert("Deleted successfully"); // Thông báo xóa thành công
```

### Dùng React-Bootstrap Alert (nếu đề yêu cầu):

```jsx
import { Alert } from "react-bootstrap";

const [message, setMessage] = useState("");
const [messageType, setMessageType] = useState(""); // 'success' hoặc 'danger'

// Hiển thị message
const showMessage = (text, type) => {
  setMessage(text);
  setMessageType(type);
  setTimeout(() => setMessage(""), 3000); // Tự ẩn sau 3 giây
};

// Trong JSX:
{
  message && (
    <Alert variant={messageType} onClose={() => setMessage("")} dismissible>
      {message}
    </Alert>
  );
}

// Sử dụng:
showMessage("Created successfully", "success");
showMessage("Error: Name is required", "danger");
```

---

## 19. TextArea

### Screen Definition ghi: **TextArea** hoặc **Multiline TextBox**

```jsx
const [description, setDescription] = useState('')

<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">Description:</Form.Label>
  <Col sm={10}>
    <Form.Control
      as="textarea"                                        // ← KHÁC: as="textarea"
      rows={3}                                              // Số dòng hiển thị
      value={description}
      onChange={(e) => setDescription(e.target.value)}
      maxLength={500}
    />
  </Col>
</Form.Group>
```

**Chú ý**: Dùng `as="textarea"` chứ KHÔNG phải `type="textarea"`.

---

## 20. Date Picker

### Screen Definition ghi: **DatePicker** hoặc **Date Input**

```jsx
const [birthDate, setBirthDate] = useState('')

<Form.Group as={Row} className="mb-2">
  <Form.Label column sm={2} className="text-end">Birth Date:</Form.Label>
  <Col sm={10}>
    <Form.Control
      type="date"
      value={birthDate}
      onChange={(e) => setBirthDate(e.target.value)}
    />
  </Col>
</Form.Group>
```

### Giá trị date:

```
value format: "2026-03-11" (yyyy-MM-dd)
```

### Validation date:

```jsx
if (!birthDate) {
  alert("Birth date is required");
  return;
}

// So sánh ngày
const selected = new Date(birthDate);
const today = new Date();
if (selected > today) {
  alert("Birth date cannot be in the future");
  return;
}
```

---

## 21. Image

### Hiển thị ảnh từ URL:

```jsx
{/* Trong table */}
<td>
  <img src={product.imageUrl} alt={product.name} width={80} height={80} />
</td>

{/* Trong detail */}
<p><b>Image:</b></p>
<img src={product.imageUrl} alt={product.name}
  style={{ maxWidth: '300px', maxHeight: '300px' }} />
```

### Input URL ảnh:

```jsx
const [imageUrl, setImageUrl] = useState('')

<Form.Control
  type="text"
  value={imageUrl}
  onChange={(e) => setImageUrl(e.target.value)}
  placeholder="https://example.com/image.jpg"
/>

{/* Preview */}
{imageUrl && <img src={imageUrl} alt="Preview" width={100} className="mt-2" />}
```

---

# PHẦN C: MẸO THI

---

## 22. Quy trình làm bài nhanh

### Bước 0: Đọc đề (5 phút)

```
Xác định:
✅ Tên entity (Shop, Product, Book, Student...)
✅ Các field + kiểu dữ liệu
✅ API URL base
✅ Validation rules
✅ Số màn hình + loại component (xem Screen Definition)
✅ Port backend (thường 8080)
```

### Bước 1: Sửa backend port (1 phút)

```yaml
# application.yaml
server:
  port: 8080
```

### Bước 2: Tạo project + cài dependencies (2 phút)

```bash
npm create vite@latest <ClassName>_<StudentID>_<ProjectCode>_PE -- --template react
cd <ClassName>_<StudentID>_<ProjectCode>_PE
npm install
npm install react-bootstrap bootstrap axios react-router-dom
```

### Bước 3: Tạo jsconfig.json (1 phút)

```json
{
  "compilerOptions": {
    "target": "ES6",
    "module": "ESNext",
    "moduleResolution": "Node",
    "jsx": "react-jsx",
    "baseUrl": "./src"
  },
  "include": ["src"]
}
```

### Bước 4: Sửa các file config (3 phút)

```
vite.config.js → thêm server.port: 5173
index.html     → đổi <title>
main.jsx       → BrowserRouter + import bootstrap CSS
```

### Bước 5: Tạo Service (2 phút)

```
src/services/XxxService.js → copy từ bài cũ, đổi API_URL + tên hàm
```

### Bước 6: Tạo XxxDetail.jsx (5 phút)

```
Đơn giản nhất → làm trước
useParams lấy id → gọi API getById → hiển thị → nút Back
```

### Bước 7: Tạo XxxList.jsx (20 phút)

```
Phức tạp nhất → chia nhỏ:
1. State variables
2. useEffect (loadAll + loadTypes)
3. Form (theo Screen Definition)
4. Validation (theo đề + Entity Java)
5. handleAddNew (tạo object + gọi API)
6. Table (render danh sách)
7. Delete (modal + confirm)
```

### Bước 8: App.jsx - Routing (2 phút)

### Bước 9: Test (5 phút)

```bash
# Terminal 1: chạy backend
cd backend-pe
mvnw.cmd spring-boot:run    # Windows
./mvnw spring-boot:run      # Mac/Linux

# Terminal 2: chạy frontend
cd <tên_thư_mục_frontend>
npm run dev
```

**Test checklist:**

- [ ] Trang chủ load được danh sách
- [ ] Form validation hoạt động
- [ ] Create thành công + alert + refresh
- [ ] Delete: modal hiện → confirm → xóa → alert → refresh
- [ ] View: click → chuyển trang detail → hiện đúng data → nút Back

---

## 23. Lỗi hay gặp và cách sửa

### Lỗi 1: "Module not found" / "Cannot find module"

```
Nguyên nhân: Chưa cài thư viện hoặc import sai đường dẫn
Sửa:
  npm install <tên-package>
  Kiểm tra đường dẫn import (../services/XxxService)
```

### Lỗi 2: CORS error (đỏ chói trong Console)

```
Nguyên nhân: Backend không cho phép frontend gọi API
Kiểm tra: Backend controller phải có @CrossOrigin(origins = "http://localhost:5173")
→ Nếu backend không có → bạn KHÔNG CẦN sửa backend (đề cho sẵn)
→ Kiểm tra lại port frontend (phải đúng 5173)
```

### Lỗi 3: "Objects are not valid as a React child"

```
Nguyên nhân: Đang render object thay vì string/number
Sai:  <td>{product}</td>             ← product là object
Đúng: <td>{product.name}</td>        ← lấy thuộc tính cụ thể
```

### Lỗi 4: "Each child in a list should have a unique 'key' prop"

```
Nguyên nhân: Thiếu prop key khi dùng .map()
Sửa: {items.map(item => <tr key={item.id}>...)}
```

### Lỗi 5: Click button trong form tự reload trang

```
Nguyên nhân: Button mặc định type="submit" → submit form → reload
Sửa 1: <Button type="button" onClick={handleClick}>
Sửa 2: Dùng onSubmit trên <Form> + e.preventDefault()
```

### Lỗi 6: State cập nhật nhưng UI không đổi

```
Nguyên nhân: Mutate state trực tiếp thay vì tạo bản mới
Sai:  shops.push(newShop); setShops(shops)
Đúng: setShops([...shops, newShop])
```

### Lỗi 7: useEffect chạy vô hạn (infinite loop)

```
Nguyên nhân: Dependency array sai
Sai:  useEffect(() => { loadData() })         ← thiếu []
Sai:  useEffect(() => { loadData() }, [data]) ← data thay đổi mỗi lần load
Đúng: useEffect(() => { loadData() }, [])     ← chỉ chạy 1 lần
```

### Lỗi 8: "Cannot read properties of null"

```
Nguyên nhân: Data chưa load xong mà đã render
Sửa: if (!product) return <p>Loading...</p>
Hoặc: {product?.name}  (optional chaining)
```

### Lỗi 9: API trả về 400/500 khi POST

```
Nguyên nhân: Dữ liệu gửi lên không đúng format
Kiểm tra:
  1. Console.log object trước khi gửi
  2. Tên field trong object JS phải KHỚP tên field trong Entity Java
  3. Kiểu dữ liệu phải đúng (number không phải string)
```

### Lỗi 10: Dropdown không hiện danh sách

```
Nguyên nhân: API /types (hoặc tương tự) chưa được gọi hoặc trả về rỗng
Kiểm tra:
  1. Backend đã có endpoint /types chưa?
  2. useEffect có gọi loadTypes() không?
  3. Console.log(res.data) xem API trả về gì
```

---

## Bảng tổng hợp nhanh: Component nào dùng gì

| Screen Definition | React-Bootstrap Component        | State type        | onChange dùng      |
| ----------------- | -------------------------------- | ----------------- | ------------------ |
| TextBox / Input   | `<Form.Control type="text">`     | `useState('')`    | `e.target.value`   |
| Number Input      | `<Form.Control type="number">`   | `useState('')`    | `e.target.value`   |
| TextArea          | `<Form.Control as="textarea">`   | `useState('')`    | `e.target.value`   |
| DropList / Select | `<Form.Select>`                  | `useState('')`    | `e.target.value`   |
| CheckBox          | `<Form.Check type="checkbox">`   | `useState(false)` | `e.target.checked` |
| Radio Button      | `<Form.Check type="radio">`      | `useState('')`    | `e.target.value`   |
| Button            | `<Button>`                       | —                 | `onClick`          |
| Hyperlink / Link  | `<Link to="...">`                | —                 | —                  |
| Table / Grid      | `<Table>`                        | `useState([])`    | —                  |
| Label             | `<Form.Label>`                   | —                 | —                  |
| Modal / Dialog    | `<Modal>`                        | `useState(false)` | —                  |
| Date Picker       | `<Form.Control type="date">`     | `useState('')`    | `e.target.value`   |
| Password          | `<Form.Control type="password">` | `useState('')`    | `e.target.value`   |
| Image             | `<img src={...}>`                | —                 | —                  |
| Alert             | `alert()` hoặc `<Alert>`         | `useState('')`    | —                  |

---

> **Tóm lại**: Khi đề thay đổi, 90% công việc nằm ở **3 file**: `Service.js`, `XxxList.jsx`, `XxxDetail.jsx`. Phần còn lại (App.jsx, index.html) chỉ đổi tên/path. Các file config (`main.jsx`, `jsconfig.json`, `vite.config.js`) **giữ nguyên**.
