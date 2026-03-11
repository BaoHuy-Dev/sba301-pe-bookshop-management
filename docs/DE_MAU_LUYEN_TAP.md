# Đề Mẫu Luyện Tập PE - SBA301 (React + Spring Boot)

> 4 đề mẫu đầy đủ với lời giải chi tiết, mỗi đề có component khác nhau để luyện tập toàn diện.

---

## Mục Lục

| Đề                                    | Tên                       | Component đặc biệt                                        | Trang                      |
| ------------------------------------- | ------------------------- | --------------------------------------------------------- | -------------------------- |
| [Đề 1](#đề-1-employee-management-emp) | Employee Management (EMP) | TextBox, DropList, Table, Modal, Link                     | Cơ bản (giống BSM)         |
| [Đề 2](#đề-2-product-management-prm)  | Product Management (PRM)  | TextBox, Number, CheckBox, TextArea, DropList             | Có Checkbox + TextArea     |
| [Đề 3](#đề-3-student-management-stm)  | Student Management (STM)  | TextBox, Date, Radio Button, DropList, Image              | Có Radio + Date + Image    |
| [Đề 4](#đề-4-course-management-crm)   | Course Management (CRM)   | TextBox, Number, CheckBox (nhiều), DropList, PUT (Update) | Có Update + Multi-Checkbox |

---

# ĐỀ 1: EMPLOYEE MANAGEMENT (EMP)

## 1.1. Yêu cầu đề bài

### Entity: Employee

```java
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80, unique = true)
    private String fullName;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String department;
}
```

### API Endpoints:

| Method | URL                          | Mô tả                    |
| ------ | ---------------------------- | ------------------------ |
| GET    | `/api/employees`             | Lấy tất cả               |
| GET    | `/api/employees/{id}`        | Lấy theo id              |
| POST   | `/api/employees`             | Tạo mới                  |
| DELETE | `/api/employees/{id}`        | Xóa                      |
| GET    | `/api/employees/departments` | Lấy danh sách department |

### Validation:

- Full Name: bắt buộc, tối đa 80 ký tự, không trùng
- Age: bắt buộc, phải > 18 và < 65
- Email: bắt buộc, tối đa 150 ký tự
- Department: bắt buộc (chọn từ dropdown)

### Màn hình:

**Screen 1 - List Screen:**

- Form: Full Name (TextBox), Age (TextBox), Email (TextBox), Department (DropList)
- Button: Add New
- Table: # No, Full Name, Department, Email, Age, Action (Delete - Button, View - Hyperlink)

**Screen 2 - Confirmation:**

- Modal xác nhận xóa

**Screen 3 - Detail Screen:**

- Hiển thị: Full Name, Email, Department, Age
- Button: Back

---

## 1.2. Lời giải Đề 1

> ### 📝 BẢN ĐỒ DÒNG THAY ĐỔI SO VỚI BSM (Đề gốc Shop)
>
> Đề 1 **cùng cấu trúc** với BSM, chỉ đổi tên entity/field/API. Dưới đây là **dòng cần đổi** trong từng code block:
>
> ---
>
> **EmployeeService.js** (11 dòng, đổi 5 dòng):
> | Dòng | BSM (cũ) | Employee (mới) |
> |------|----------|----------------|
> | 3 | `'http://localhost:8080/myapp/shops'` | `'http://localhost:8080/api/employees'` |
> | 7 | `create(shop)` | `create(employee)` |
> | 8 | `deleteShop` | `deleteEmployee` |
> | 9 | `getTypes` + `/types` | `getDepartments` + `/departments` |
> | 11 | `deleteShop, getTypes` | `deleteEmployee, getDepartments` |
>
> ---
>
> **App.jsx** (14 dòng, đổi 4 dòng):
> | Dòng | BSM (cũ) | Employee (mới) |
> |------|----------|----------------|
> | 2 | `import ShopList` | `import EmployeeList` |
> | 3 | `import ShopDetail` | `import EmployeeDetail` |
> | 8 | `element={<ShopList />}` | `element={<EmployeeList />}` |
> | 9 | `path="/shop/:id"`, `<ShopDetail />` | `path="/employee/:id"`, `<EmployeeDetail />` |
>
> ---
>
> **EmployeeList.jsx** (~250 dòng formatted, đổi ~60 dòng theo vùng):
> | Vùng | Dòng | Thay đổi |
> |------|------|----------|
> | Import | 12 | `ShopService` → `EmployeeService` |
> | Tên function | 14 | `ShopList` → `EmployeeList` |
> | State (6 biến) | 15-20 | `shops/types/name/openTime/owner/type` → `employees/departments/fullName/age/email/department` |
> | useEffect | 26-27 | `loadShops/loadTypes` → `loadEmployees/loadDepartments` |
> | Load functions | 30-36 | Đổi tên hàm + service + setState |
> | Validation | 40-68 | Đổi field name + rule: `fullName ≤80`, `age >18 & <65`, `email ≤150`, `department required` |
> | Object tạo mới | 70-75 | `{ fullName, age: ageNum, email, department }` |
> | API call + reset | 77-88 | `EmployeeService.create(employee)`, alert `"Employee"`, reset 4 state |
> | Delete handler | 91-97 | Đổi param name + `EmployeeService.deleteEmployee` + `loadEmployees` |
> | `<h2>` title | ~107 | `"Employee Management"` |
> | Form (4 field) | ~110-175 | Đổi label, state binding, maxLength cho 4 field |
> | `<h4>` subtitle | ~180 | `"Employee List"` |
> | Table thead | ~184-191 | `Full Name / Department / Email / Age` |
> | Table tbody | ~193-215 | `.fullName`, `.department`, `.email`, `.age`, Link `/employee/` |
> | Modal body | ~225 | `deleteTarget?.fullName` |
> | Export | cuối | `EmployeeList` |
>
> ---
>
> **EmployeeDetail.jsx** (~43 dòng, đổi 10 dòng):
> | Dòng | BSM (cũ) | Employee (mới) |
> |------|----------|----------------|
> | 4 | `ShopService` | `EmployeeService` |
> | 6 | `ShopDetail` | `EmployeeDetail` |
> | 9 | `shop` | `employee` |
> | 12 | `ShopService.getById`, `setShop` | `EmployeeService.getById`, `setEmployee` |
> | 15 | `!shop` | `!employee` |
> | 24 | `shop.name` → `Shop Name:` | `employee.fullName` → `Full Name:` |
> | 27 | `shop.owner` → `Owner:` | `employee.email` → `Email:` |
> | 30 | `shop.type` → `Type:` | `employee.department` → `Department:` |
> | 33 | `shop.openTime` → `Open time:` | `employee.age` → `Age:` |
> | 37 | `"Quay lai"` | `"Back"` |
> | 43 | `ShopDetail` | `EmployeeDetail` |

### File 1: `src/services/EmployeeService.js`

```javascript
import axios from "axios";

const API_URL = "http://localhost:8080/api/employees";

const getAll = () => axios.get(API_URL);
const getById = (id) => axios.get(`${API_URL}/${id}`);
const create = (employee) => axios.post(API_URL, employee);
const deleteEmployee = (id) => axios.delete(`${API_URL}/${id}`);
const getDepartments = () => axios.get(`${API_URL}/departments`);

export default { getAll, getById, create, deleteEmployee, getDepartments };
```

### File 2: `src/App.jsx`

```jsx
import { Routes, Route } from "react-router-dom";
import EmployeeList from "./components/EmployeeList";
import EmployeeDetail from "./components/EmployeeDetail";

function App() {
  return (
    <Routes>
      <Route path="/" element={<EmployeeList />} />
      <Route path="/employee/:id" element={<EmployeeDetail />} />
    </Routes>
  );
}

export default App;
```

### File 3: `src/components/EmployeeList.jsx`

```jsx
import { useState, useEffect } from "react";
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";

function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [fullName, setFullName] = useState("");
  const [age, setAge] = useState("");
  const [email, setEmail] = useState("");
  const [department, setDepartment] = useState("");

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() => {
    loadEmployees();
    loadDepartments();
  }, []);

  const loadEmployees = () => {
    EmployeeService.getAll().then((res) => setEmployees(res.data));
  };

  const loadDepartments = () => {
    EmployeeService.getDepartments().then((res) => setDepartments(res.data));
  };

  const handleAddNew = () => {
    // Validation
    if (!fullName.trim()) {
      alert("Full Name is required");
      return;
    }
    if (fullName.trim().length > 80) {
      alert("Full Name must be at most 80 characters");
      return;
    }
    if (!age) {
      alert("Age is required");
      return;
    }
    const ageNum = Number(age);
    if (isNaN(ageNum) || ageNum <= 18 || ageNum >= 65) {
      alert("Age must be greater than 18 and less than 65");
      return;
    }
    if (!email.trim()) {
      alert("Email is required");
      return;
    }
    if (email.trim().length > 150) {
      alert("Email must be at most 150 characters");
      return;
    }
    if (!department) {
      alert("Department is required");
      return;
    }

    const employee = {
      fullName: fullName.trim(),
      age: ageNum,
      email: email.trim(),
      department,
    };

    EmployeeService.create(employee)
      .then(() => {
        alert("Created new Employee successfully");
        loadEmployees();
        setFullName("");
        setAge("");
        setEmail("");
        setDepartment("");
      })
      .catch((err) => {
        alert(err.response?.data?.message || "Error creating employee");
      });
  };

  const handleDeleteClick = (emp) => {
    setDeleteTarget(emp);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () => {
    EmployeeService.deleteEmployee(deleteTarget.id).then(() => {
      alert("Deleted successfully");
      loadEmployees();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () => {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    <Container>
      <h2 className="mt-3 mb-3">
        <b>Employee Management</b>
      </h2>
      <Form>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Full Name:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
              maxLength={80}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Age:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={age}
              onChange={(e) => setAge(e.target.value)}
              maxLength={2}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Email:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              maxLength={150}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Department:
          </Form.Label>
          <Col sm={10}>
            <Form.Select
              value={department}
              onChange={(e) => setDepartment(e.target.value)}
            >
              <option value="">-- Select --</option>
              {departments.map((d) => (
                <option key={d} value={d}>
                  {d}
                </option>
              ))}
            </Form.Select>
          </Col>
        </Form.Group>
        <Row className="mb-3">
          <Col sm={{ span: 10, offset: 2 }}>
            <Button variant="primary" onClick={handleAddNew}>
              Add New
            </Button>
          </Col>
        </Row>
      </Form>

      <h4>
        <b>Employee List</b>
      </h4>
      <Table bordered hover>
        <thead>
          <tr>
            <th># No</th>
            <th>Full Name</th>
            <th>Department</th>
            <th>Email</th>
            <th>Age</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((emp, index) => (
            <tr key={emp.id}>
              <td>{String(index + 1).padStart(2, "0")}</td>
              <td>{emp.fullName}</td>
              <td>{emp.department}</td>
              <td>{emp.email}</td>
              <td>{emp.age}</td>
              <td>
                <Button
                  variant="danger"
                  size="sm"
                  onClick={() => handleDeleteClick(emp)}
                >
                  Delete
                </Button>
                {" | "}
                <Link to={`/employee/${emp.id}`}>View</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal show={showConfirm} onHide={handleDeleteClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to delete "{deleteTarget?.fullName}"?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleDeleteConfirm}>
            Yes
          </Button>
          <Button variant="secondary" onClick={handleDeleteClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default EmployeeList;
```

### File 4: `src/components/EmployeeDetail.jsx`

```jsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import EmployeeService from "../services/EmployeeService";

function EmployeeDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    EmployeeService.getById(id).then((res) => setEmployee(res.data));
  }, [id]);

  if (!employee) return <Container className="mt-3">Loading...</Container>;

  return (
    <Container className="mt-3">
      <h2>
        <b>VIEW DETAILS</b>
      </h2>
      <div className="mt-4 ms-4">
        <p>
          <b>Full Name:</b> {employee.fullName}
        </p>
        <p>
          <b>Email:</b> {employee.email}
        </p>
        <p>
          <b>Department:</b> {employee.department}
        </p>
        <p>
          <b>Age:</b> {employee.age}
        </p>
      </div>
      <Button variant="outline-primary" onClick={() => navigate("/")}>
        Back
      </Button>
    </Container>
  );
}

export default EmployeeDetail;
```

### Điểm cần lưu ý Đề 1:

- Cấu trúc **giống hệt BSM**, chỉ đổi tên entity + field + API URL
- Validation age: `> 18 AND < 65` (không bao gồm 18 và 65)
- Email không cần validation format (đề chỉ yêu cầu required + maxLength)

---

# ĐỀ 2: PRODUCT MANAGEMENT (PRM)

## 2.1. Yêu cầu đề bài

### Entity: Product

```java
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String productName;

    @Column(nullable = false)
    private double price;

    @Column(length = 500)
    private String description;      // ← TextArea

    @Column(nullable = false)
    private boolean inStock;         // ← CheckBox

    @Column(nullable = false)
    private String category;
}
```

### API Endpoints:

| Method | URL                        | Mô tả                  |
| ------ | -------------------------- | ---------------------- |
| GET    | `/api/products`            | Lấy tất cả             |
| GET    | `/api/products/{id}`       | Lấy theo id            |
| POST   | `/api/products`            | Tạo mới                |
| DELETE | `/api/products/{id}`       | Xóa                    |
| GET    | `/api/products/categories` | Lấy danh sách category |

### Validation:

- Product Name: bắt buộc, tối đa 100 ký tự, không trùng
- Price: bắt buộc, phải > 0 và <= 99999
- Description: không bắt buộc, tối đa 500 ký tự
- In Stock: mặc định false (checkbox)
- Category: bắt buộc (chọn từ dropdown)

### Màn hình:

**Screen 1 - List Screen:**

- Form: Product Name (TextBox), Price (TextBox), Description (TextArea), In Stock (CheckBox), Category (DropList)
- Button: Add New
- Table: # No, Product Name, Category, Price, In Stock, Action (Delete - Button, View - Hyperlink)

**Screen 2 - Confirmation:** Modal xác nhận xóa

**Screen 3 - Detail Screen:** Hiển thị: Product Name, Category, Price, In Stock, Description

---

## 2.2. Lời giải Đề 2

> ### 📝 BẢN ĐỒ DÒNG THAY ĐỔI SO VỚI BSM
>
> Đề 2 thêm **CheckBox** (`inStock`) + **TextArea** (`description`). Ngoài đổi tên, có **dòng MỚI HOÀN TOÀN** (đánh dấu 🆕).
>
> ---
>
> **ProductService.js** (đổi 5 dòng, giống pattern Đề 1):
> | Dòng | Thay đổi |
> |------|----------|
> | 3 | URL → `'/api/products'` |
> | 7 | `create(product)` |
> | 8 | `deleteProduct` |
> | 9 | `getCategories` + `/categories` |
> | 11 | export tương ứng |
>
> ---
>
> **App.jsx** (đổi 4 dòng):
> | Dòng | Thay đổi |
> |------|----------|
> | 2-3 | import `ProductList`, `ProductDetail` |
> | 8 | `<ProductList />` |
> | 9 | `"/product/:id"`, `<ProductDetail />` |
>
> ---
>
> **ProductList.jsx** — KHÁC BSM nhiều nhất:
> | Vùng | Dòng | Thay đổi |
> |------|------|----------|
> | Import | 12 | `ProductService` |
> | Tên function | 14 | `ProductList` |
> | State | 15-18 | `products/categories/productName/price` (đổi tên) |
> | 🆕 State mới | 19 | `const [description, setDescription] = useState("")` — **TextArea** |
> | 🆕 State mới | 20 | `const [inStock, setInStock] = useState(false)` — **CheckBox (boolean!)** |
> | State | 21 | `category` (đổi tên) |
> | Load functions | 30-36 | `loadProducts`, `loadCategories`, `ProductService` |
> | Validation | 40-56 | `productName ≤100`, `price >0 & ≤99999` |
> | 🆕 Validation | 58-61 | `description.length > 500` — **không bắt buộc, chỉ check max** |
> | Object | 68-73 | Thêm `description`, `inStock` (boolean) |
> | Reset | 80-84 | Thêm `setDescription("")`, `setInStock(false)` |
> | 🆕 Form TextArea | ~136-147 | `<Form.Control as="textarea" rows={3}>` — **KHÔNG phải type="textarea"** |
> | 🆕 Form CheckBox | ~150-158 | `<Form.Check type="checkbox" checked={inStock} onChange={e.target.checked}>` |
> | Table thead | ~184 | Thêm cột `In Stock` |
> | 🆕 Table tbody | ~199 | `{product.inStock ? "Yes" : "No"}` — **Boolean → text** |
> | Modal body | ~221 | `deleteTarget?.productName` |
>
> ---
>
> **ProductDetail.jsx** (đổi fields + thêm 2 dòng mới):
> | Dòng | Thay đổi |
> |------|----------|
> | 4 | `ProductService` |
> | 6, 9, 12, 15 | Đổi tên entity |
> | 24-33 | 5 field (đổi tên) |
> | 🆕 30 | `{product.inStock ? "Yes" : "No"}` — boolean hiển thị |
> | 🆕 33 | `{product.description}` — field mới |

### File 1: `src/services/ProductService.js`

```javascript
import axios from "axios";

const API_URL = "http://localhost:8080/api/products";

const getAll = () => axios.get(API_URL);
const getById = (id) => axios.get(`${API_URL}/${id}`);
const create = (product) => axios.post(API_URL, product);
const deleteProduct = (id) => axios.delete(`${API_URL}/${id}`);
const getCategories = () => axios.get(`${API_URL}/categories`);

export default { getAll, getById, create, deleteProduct, getCategories };
```

### File 2: `src/App.jsx`

```jsx
import { Routes, Route } from "react-router-dom";
import ProductList from "./components/ProductList";
import ProductDetail from "./components/ProductDetail";

function App() {
  return (
    <Routes>
      <Route path="/" element={<ProductList />} />
      <Route path="/product/:id" element={<ProductDetail />} />
    </Routes>
  );
}

export default App;
```

### File 3: `src/components/ProductList.jsx`

> **Điểm khác so với BSM**: Có **CheckBox** (`inStock`) và **TextArea** (`description`)

```jsx
import { useState, useEffect } from "react";
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import ProductService from "../services/ProductService";

function ProductList() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [productName, setProductName] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState(""); // ← MỚI: TextArea
  const [inStock, setInStock] = useState(false); // ← MỚI: CheckBox (boolean)
  const [category, setCategory] = useState("");

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() => {
    loadProducts();
    loadCategories();
  }, []);

  const loadProducts = () => {
    ProductService.getAll().then((res) => setProducts(res.data));
  };

  const loadCategories = () => {
    ProductService.getCategories().then((res) => setCategories(res.data));
  };

  const handleAddNew = () => {
    // Validation
    if (!productName.trim()) {
      alert("Product Name is required");
      return;
    }
    if (productName.trim().length > 100) {
      alert("Product Name must be at most 100 characters");
      return;
    }

    if (!price) {
      alert("Price is required");
      return;
    }
    const priceNum = Number(price);
    if (isNaN(priceNum) || priceNum <= 0 || priceNum > 99999) {
      alert("Price must be greater than 0 and at most 99999");
      return;
    }

    // Description: KHÔNG bắt buộc, chỉ check maxLength
    if (description.trim().length > 500) {
      alert("Description must be at most 500 characters");
      return;
    }

    if (!category) {
      alert("Category is required");
      return;
    }

    // ★ inStock KHÔNG cần validation (checkbox, mặc định false)

    const product = {
      productName: productName.trim(),
      price: priceNum,
      description: description.trim(),
      inStock, // ← boolean: true hoặc false
      category,
    };

    ProductService.create(product)
      .then(() => {
        alert("Created new Product successfully");
        loadProducts();
        setProductName("");
        setPrice("");
        setDescription("");
        setInStock(false); // ← Reset về false
        setCategory("");
      })
      .catch((err) => {
        alert(err.response?.data?.message || "Error creating product");
      });
  };

  const handleDeleteClick = (product) => {
    setDeleteTarget(product);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () => {
    ProductService.deleteProduct(deleteTarget.id).then(() => {
      alert("Deleted successfully");
      loadProducts();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () => {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    <Container>
      <h2 className="mt-3 mb-3">
        <b>Product Management</b>
      </h2>
      <Form>
        {/* TextBox - Product Name */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Product Name:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={productName}
              onChange={(e) => setProductName(e.target.value)}
              maxLength={100}
            />
          </Col>
        </Form.Group>

        {/* TextBox - Price (nhập số) */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Price:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={price}
              onChange={(e) => setPrice(e.target.value)}
            />
          </Col>
        </Form.Group>

        {/* ★ TextArea - Description */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Description:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              as="textarea" // ← as="textarea" chứ KHÔNG phải type="textarea"
              rows={3}
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              maxLength={500}
            />
          </Col>
        </Form.Group>

        {/* ★ CheckBox - In Stock */}
        <Form.Group as={Row} className="mb-2">
          <Col sm={{ span: 10, offset: 2 }}>
            <Form.Check
              type="checkbox"
              label="In Stock"
              checked={inStock} // ← dùng checked (boolean)
              onChange={(e) => setInStock(e.target.checked)} // ← dùng e.target.CHECKED
            />
          </Col>
        </Form.Group>

        {/* DropList - Category */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Category:
          </Form.Label>
          <Col sm={10}>
            <Form.Select
              value={category}
              onChange={(e) => setCategory(e.target.value)}
            >
              <option value="">-- Select --</option>
              {categories.map((c) => (
                <option key={c} value={c}>
                  {c}
                </option>
              ))}
            </Form.Select>
          </Col>
        </Form.Group>

        <Row className="mb-3">
          <Col sm={{ span: 10, offset: 2 }}>
            <Button variant="primary" onClick={handleAddNew}>
              Add New
            </Button>
          </Col>
        </Row>
      </Form>

      <h4>
        <b>Product List</b>
      </h4>
      <Table bordered hover>
        <thead>
          <tr>
            <th># No</th>
            <th>Product Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>In Stock</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product, index) => (
            <tr key={product.id}>
              <td>{String(index + 1).padStart(2, "0")}</td>
              <td>{product.productName}</td>
              <td>{product.category}</td>
              <td>{product.price}</td>
              <td>{product.inStock ? "Yes" : "No"}</td>{" "}
              {/* ← Boolean hiển thị */}
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
        </tbody>
      </Table>

      {/* Modal xác nhận xóa */}
      <Modal show={showConfirm} onHide={handleDeleteClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to delete "{deleteTarget?.productName}"?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleDeleteConfirm}>
            Yes
          </Button>
          <Button variant="secondary" onClick={handleDeleteClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default ProductList;
```

### File 4: `src/components/ProductDetail.jsx`

```jsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import ProductService from "../services/ProductService";

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
        </p>
        <p>
          <b>Category:</b> {product.category}
        </p>
        <p>
          <b>Price:</b> {product.price}
        </p>
        <p>
          <b>In Stock:</b> {product.inStock ? "Yes" : "No"}
        </p>
        <p>
          <b>Description:</b> {product.description}
        </p>
      </div>
      <Button variant="outline-primary" onClick={() => navigate("/")}>
        Back
      </Button>
    </Container>
  );
}

export default ProductDetail;
```

### Điểm cần lưu ý Đề 2:

| Component                      | Điểm khác so với BSM                                                   |
| ------------------------------ | ---------------------------------------------------------------------- |
| **CheckBox**                   | `checked={inStock}` + `e.target.checked` (KHÔNG phải `.value`)         |
| **TextArea**                   | `<Form.Control as="textarea" rows={3}>` (KHÔNG phải `type="textarea"`) |
| **Boolean trong table**        | `{product.inStock ? 'Yes' : 'No'}`                                     |
| **Reset checkbox**             | `setInStock(false)` (không phải `''`)                                  |
| **Description không bắt buộc** | Chỉ validate maxLength, KHÔNG check empty                              |

---

# ĐỀ 3: STUDENT MANAGEMENT (STM)

## 3.1. Yêu cầu đề bài

### Entity: Student

```java
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String studentName;

    @Column(nullable = false)
    private String birthDate;            // ← Date (lưu dạng String "yyyy-MM-dd")

    @Column(nullable = false)
    private String gender;               // ← Radio Button ("Male" / "Female")

    @Column(nullable = false)
    private String major;

    @Column(length = 255)
    private String avatarUrl;            // ← Image URL
}
```

### API Endpoints:

| Method | URL                    | Mô tả               |
| ------ | ---------------------- | ------------------- |
| GET    | `/api/students`        | Lấy tất cả          |
| GET    | `/api/students/{id}`   | Lấy theo id         |
| POST   | `/api/students`        | Tạo mới             |
| DELETE | `/api/students/{id}`   | Xóa                 |
| GET    | `/api/students/majors` | Lấy danh sách major |

### Validation:

- Student Name: bắt buộc, tối đa 100 ký tự, không trùng
- Birth Date: bắt buộc, không được là ngày tương lai
- Gender: bắt buộc (radio button)
- Major: bắt buộc (dropdown)
- Avatar URL: không bắt buộc

### Màn hình:

**Screen 1 - List Screen:**

- Form: Student Name (TextBox), Birth Date (DatePicker), Gender (Radio Button: Male/Female), Major (DropList), Avatar URL (TextBox)
- Button: Add New
- Table: # No, Avatar (Image), Student Name, Gender, Major, Birth Date, Action (Delete - Button, View - Hyperlink)

**Screen 2 - Confirmation:** Modal xác nhận xóa

**Screen 3 - Detail Screen:** Hiển thị: Avatar (Image), Student Name, Gender, Major, Birth Date

---

## 3.2. Lời giải Đề 3

> ### 📝 BẢN ĐỒ DÒNG THAY ĐỔI SO VỚI BSM
>
> Đề 3 thêm **Radio Button** (`gender`), **Date Picker** (`birthDate`), **Image** (`avatarUrl`). Dòng mới đánh dấu 🆕.
>
> ---
>
> **StudentService.js** (đổi 5 dòng, giống pattern):
> | Dòng | Thay đổi |
> |------|----------|
> | 3 | URL → `'/api/students'` |
> | 7 | `create(student)` |
> | 8 | `deleteStudent` |
> | 9 | `getMajors` + `/majors` |
> | 11 | export tương ứng |
>
> ---
>
> **App.jsx** (đổi 4 dòng):
> | Dòng | Thay đổi |
> |------|----------|
> | 2-3 | import `StudentList`, `StudentDetail` |
> | 8 | `<StudentList />` |
> | 9 | `"/student/:id"`, `<StudentDetail />` |
>
> ---
>
> **StudentList.jsx** — Thêm Radio + Date + Image:
> | Vùng | Dòng | Thay đổi |
> |------|------|----------|
> | Import | 12 | `StudentService` |
> | State | 15-16 | `students/majors` (đổi tên) |
> | State | 17 | `studentName` (đổi tên) |
> | 🆕 State | 18 | `const [birthDate, setBirthDate] = useState("")` — **Date** |
> | 🆕 State | 19 | `const [gender, setGender] = useState("")` — **Radio (String!)** |
> | State | 20 | `major` (đổi tên) |
> | 🆕 State | 21 | `const [avatarUrl, setAvatarUrl] = useState("")` — **Image URL** |
> | Load | 30-36 | `loadStudents`, `loadMajors`, `StudentService` |
> | Validation | 40-46 | `studentName ≤100` |
> | 🆕 Validation Date | 48-55 | `new Date(birthDate) > today` → **không cho ngày tương lai** |
> | 🆕 Validation Radio | 57-58 | `if (!gender)` — **radio cũng cần validate** |
> | Object | 68-74 | `{ studentName, birthDate, gender, major, avatarUrl }` |
> | Reset | 80-85 | Thêm `setBirthDate("")`, `setGender("")`, `setAvatarUrl("")` |
> | 🆕 Form Date | ~130-138 | `<Form.Control type="date">` — **type="date" (không phải text)** |
> | 🆕 Form Radio | ~141-163 | 2x `<Form.Check type="radio" name="gender" checked={gender === "Male"}>` |
> | | | ⚠️ Radio dùng `e.target.value` (KHÁC checkbox dùng `e.target.checked`) |
> | | | ⚠️ `name="gender"` bắt buộc — cùng name = cùng nhóm radio |
> | | | ⚠️ `inline` — nằm ngang |
> | 🆕 Form Image URL | ~177-185 | TextBox thường + placeholder |
> | Table thead | ~194 | Thêm cột `Avatar`, `Gender`, `Birth Date` |
> | 🆕 Table Image | ~201-211 | `<img src={stu.avatarUrl} width={50} height={50}>` + check null: `stu.avatarUrl ? <img> : "N/A"` |
> | Table tbody | ~212-216 | `.studentName`, `.gender`, `.major`, `.birthDate` |
> | Modal body | ~235 | `deleteTarget?.studentName` |
>
> ---
>
> **StudentDetail.jsx** — Thêm Image hiển thị:
> | Dòng | Thay đổi |
> |------|----------|
> | 4, 6, 9, 12, 15 | Đổi tên entity |
> | 🆕 23-30 | `{student.avatarUrl && <img src={...} style={{maxWidth:"200px"}}>}` — **ảnh lớn trong detail** |
> | 31-37 | 4 field: `.studentName`, `.gender`, `.major`, `.birthDate` |

### File 1: `src/services/StudentService.js`

```javascript
import axios from "axios";

const API_URL = "http://localhost:8080/api/students";

const getAll = () => axios.get(API_URL);
const getById = (id) => axios.get(`${API_URL}/${id}`);
const create = (student) => axios.post(API_URL, student);
const deleteStudent = (id) => axios.delete(`${API_URL}/${id}`);
const getMajors = () => axios.get(`${API_URL}/majors`);

export default { getAll, getById, create, deleteStudent, getMajors };
```

### File 2: `src/App.jsx`

```jsx
import { Routes, Route } from "react-router-dom";
import StudentList from "./components/StudentList";
import StudentDetail from "./components/StudentDetail";

function App() {
  return (
    <Routes>
      <Route path="/" element={<StudentList />} />
      <Route path="/student/:id" element={<StudentDetail />} />
    </Routes>
  );
}

export default App;
```

### File 3: `src/components/StudentList.jsx`

> **Điểm khác so với BSM**: Có **Radio Button** (`gender`), **Date Picker** (`birthDate`), **Image** (`avatarUrl`)

```jsx
import { useState, useEffect } from "react";
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import StudentService from "../services/StudentService";

function StudentList() {
  const [students, setStudents] = useState([]);
  const [majors, setMajors] = useState([]);
  const [studentName, setStudentName] = useState("");
  const [birthDate, setBirthDate] = useState(""); // ← MỚI: Date
  const [gender, setGender] = useState(""); // ← MỚI: Radio
  const [major, setMajor] = useState("");
  const [avatarUrl, setAvatarUrl] = useState(""); // ← MỚI: Image URL

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() => {
    loadStudents();
    loadMajors();
  }, []);

  const loadStudents = () => {
    StudentService.getAll().then((res) => setStudents(res.data));
  };

  const loadMajors = () => {
    StudentService.getMajors().then((res) => setMajors(res.data));
  };

  const handleAddNew = () => {
    // Validation
    if (!studentName.trim()) {
      alert("Student Name is required");
      return;
    }
    if (studentName.trim().length > 100) {
      alert("Student Name must be at most 100 characters");
      return;
    }

    // ★ Date validation
    if (!birthDate) {
      alert("Birth Date is required");
      return;
    }
    const selectedDate = new Date(birthDate);
    const today = new Date();
    today.setHours(0, 0, 0, 0); // Reset giờ để so sánh chính xác
    if (selectedDate > today) {
      alert("Birth Date cannot be in the future");
      return;
    }

    // ★ Radio validation
    if (!gender) {
      alert("Gender is required");
      return;
    }

    if (!major) {
      alert("Major is required");
      return;
    }

    // avatarUrl: KHÔNG bắt buộc

    const student = {
      studentName: studentName.trim(),
      birthDate, // Format: "2000-05-15" (yyyy-MM-dd)
      gender, // "Male" hoặc "Female"
      major,
      avatarUrl: avatarUrl.trim(),
    };

    StudentService.create(student)
      .then(() => {
        alert("Created new Student successfully");
        loadStudents();
        setStudentName("");
        setBirthDate("");
        setGender(""); // ← Reset radio
        setMajor("");
        setAvatarUrl("");
      })
      .catch((err) => {
        alert(err.response?.data?.message || "Error creating student");
      });
  };

  const handleDeleteClick = (student) => {
    setDeleteTarget(student);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () => {
    StudentService.deleteStudent(deleteTarget.id).then(() => {
      alert("Deleted successfully");
      loadStudents();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () => {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    <Container>
      <h2 className="mt-3 mb-3">
        <b>Student Management</b>
      </h2>
      <Form>
        {/* TextBox - Student Name */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Student Name:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={studentName}
              onChange={(e) => setStudentName(e.target.value)}
              maxLength={100}
            />
          </Col>
        </Form.Group>

        {/* ★ Date Picker - Birth Date */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Birth Date:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="date" // ← type="date"
              value={birthDate}
              onChange={(e) => setBirthDate(e.target.value)}
            />
          </Col>
        </Form.Group>

        {/* ★ Radio Button - Gender */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Gender:
          </Form.Label>
          <Col sm={10}>
            <Form.Check
              inline // ← Nằm ngang
              type="radio" // ← type="radio"
              label="Male"
              name="gender" // ← Cùng name = cùng nhóm
              value="Male"
              checked={gender === "Male"} // ← So sánh với state
              onChange={(e) => setGender(e.target.value)} // ← Dùng .value (KHÁC checkbox)
            />
            <Form.Check
              inline
              type="radio"
              label="Female"
              name="gender" // ← Cùng name "gender"
              value="Female"
              checked={gender === "Female"}
              onChange={(e) => setGender(e.target.value)}
            />
          </Col>
        </Form.Group>

        {/* DropList - Major */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Major:
          </Form.Label>
          <Col sm={10}>
            <Form.Select
              value={major}
              onChange={(e) => setMajor(e.target.value)}
            >
              <option value="">-- Select --</option>
              {majors.map((m) => (
                <option key={m} value={m}>
                  {m}
                </option>
              ))}
            </Form.Select>
          </Col>
        </Form.Group>

        {/* TextBox - Avatar URL (không bắt buộc) */}
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Avatar URL:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={avatarUrl}
              onChange={(e) => setAvatarUrl(e.target.value)}
              placeholder="https://example.com/image.jpg"
            />
          </Col>
        </Form.Group>

        <Row className="mb-3">
          <Col sm={{ span: 10, offset: 2 }}>
            <Button variant="primary" onClick={handleAddNew}>
              Add New
            </Button>
          </Col>
        </Row>
      </Form>

      <h4>
        <b>Student List</b>
      </h4>
      <Table bordered hover>
        <thead>
          <tr>
            <th># No</th>
            <th>Avatar</th>
            <th>Student Name</th>
            <th>Gender</th>
            <th>Major</th>
            <th>Birth Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {students.map((stu, index) => (
            <tr key={stu.id}>
              <td>{String(index + 1).padStart(2, "0")}</td>
              {/* ★ Image trong table */}
              <td>
                {stu.avatarUrl ? (
                  <img
                    src={stu.avatarUrl}
                    alt={stu.studentName}
                    width={50}
                    height={50}
                    style={{ objectFit: "cover", borderRadius: "50%" }}
                  />
                ) : (
                  "N/A"
                )}
              </td>
              <td>{stu.studentName}</td>
              <td>{stu.gender}</td>
              <td>{stu.major}</td>
              <td>{stu.birthDate}</td>
              <td>
                <Button
                  variant="danger"
                  size="sm"
                  onClick={() => handleDeleteClick(stu)}
                >
                  Delete
                </Button>
                {" | "}
                <Link to={`/student/${stu.id}`}>View</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      {/* Modal */}
      <Modal show={showConfirm} onHide={handleDeleteClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to delete "{deleteTarget?.studentName}"?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleDeleteConfirm}>
            Yes
          </Button>
          <Button variant="secondary" onClick={handleDeleteClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default StudentList;
```

### File 4: `src/components/StudentDetail.jsx`

```jsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import StudentService from "../services/StudentService";

function StudentDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [student, setStudent] = useState(null);

  useEffect(() => {
    StudentService.getById(id).then((res) => setStudent(res.data));
  }, [id]);

  if (!student) return <Container className="mt-3">Loading...</Container>;

  return (
    <Container className="mt-3">
      <h2>
        <b>VIEW DETAILS</b>
      </h2>
      <div className="mt-4 ms-4">
        {/* ★ Image trong detail */}
        {student.avatarUrl && (
          <div className="mb-3">
            <img
              src={student.avatarUrl}
              alt={student.studentName}
              style={{
                maxWidth: "200px",
                maxHeight: "200px",
                borderRadius: "8px",
              }}
            />
          </div>
        )}
        <p>
          <b>Student Name:</b> {student.studentName}
        </p>
        <p>
          <b>Gender:</b> {student.gender}
        </p>
        <p>
          <b>Major:</b> {student.major}
        </p>
        <p>
          <b>Birth Date:</b> {student.birthDate}
        </p>
      </div>
      <Button variant="outline-primary" onClick={() => navigate("/")}>
        Back
      </Button>
    </Container>
  );
}

export default StudentDetail;
```

### Điểm cần lưu ý Đề 3:

| Component              | Cú pháp quan trọng                                                                                      |
| ---------------------- | ------------------------------------------------------------------------------------------------------- |
| **Radio Button**       | `type="radio"`, `name="gender"` (cùng nhóm), `checked={gender === 'Male'}`, `onChange → e.target.value` |
| **Date Picker**        | `type="date"`, value format `"yyyy-MM-dd"`, validate bằng `new Date()`                                  |
| **Image trong table**  | `<img src={url} width={50} height={50} />`, kiểm tra `url` có tồn tại không                             |
| **Image trong detail** | `{student.avatarUrl && <img ... />}` - chỉ hiện nếu có URL                                              |
| **Radio vs Checkbox**  | Radio: `e.target.value` / Checkbox: `e.target.checked`                                                  |

---

# ĐỀ 4: COURSE MANAGEMENT (CRM)

## 4.1. Yêu cầu đề bài

### Entity: Course

```java
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String courseName;

    @Column(nullable = false)
    private int credits;

    @Column(nullable = false, length = 80)
    private String instructor;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private boolean active;
}
```

### API Endpoints:

| Method  | URL                     | Mô tả               |
| ------- | ----------------------- | ------------------- |
| GET     | `/api/courses`          | Lấy tất cả          |
| GET     | `/api/courses/{id}`     | Lấy theo id         |
| POST    | `/api/courses`          | Tạo mới             |
| **PUT** | **`/api/courses/{id}`** | **Cập nhật** ← MỚI  |
| DELETE  | `/api/courses/{id}`     | Xóa                 |
| GET     | `/api/courses/levels`   | Lấy danh sách level |

### Validation:

- Course Name: bắt buộc, tối đa 100 ký tự, không trùng
- Credits: bắt buộc, từ 1 đến 10 (bao gồm 1 và 10)
- Instructor: bắt buộc, tối đa 80 ký tự
- Level: bắt buộc (dropdown)
- Active: mặc định true (checkbox)

### Màn hình:

**Screen 1 - List Screen:**

- Form: Course Name (TextBox), Credits (TextBox), Instructor (TextBox), Level (DropList), Active (CheckBox)
- Button: Add New / Update (tùy mode), Cancel (khi đang edit)
- Table: # No, Course Name, Level, Instructor, Credits, Active, Action (**Edit** - Button, Delete - Button, View - Hyperlink)

**Screen 2 - Confirmation:** Modal xác nhận xóa

**Screen 3 - Detail Screen:** Hiển thị: Course Name, Instructor, Level, Credits, Active

---

## 4.2. Lời giải Đề 4

> ### 📝 BẢN ĐỒ DÒNG THAY ĐỔI SO VỚI BSM
>
> Đề 4 thêm **PUT (Update)** + **Edit mode** + **CheckBox**. Phức tạp nhất — có nhiều logic MỚI. 🆕 = dòng mới hoàn toàn.
>
> ---
>
> **CourseService.js** (đổi 5 dòng + 🆕 1 dòng):
> | Dòng | Thay đổi |
> |------|----------|
> | 3 | URL → `'/api/courses'` |
> | 7 | `create(course)` |
> | 🆕 8 | `const update = (id, course) => axios.put(...)` — **THÊM HÀM PUT** |
> | 9 | `deleteCourse` |
> | 10 | `getLevels` + `/levels` |
> | 12 | export thêm `update` |
>
> ---
>
> **App.jsx** (đổi 4 dòng):
> | Dòng | Thay đổi |
> |------|----------|
> | 2-3 | import `CourseList`, `CourseDetail` |
> | 8 | `<CourseList />` |
> | 9 | `"/course/:id"`, `<CourseDetail />` |
>
> ---
>
> **CourseList.jsx** — PHỨC TẠP NHẤT (thêm Edit mode):
> | Vùng | Dòng | Thay đổi |
> |------|------|----------|
> | Import | 12 | `CourseService` |
> | State | 15-19 | `courses/levels/courseName/credits/instructor` (đổi tên) |
> | State | 20 | `level` (đổi tên) |
> | 🆕 State checkbox | 21 | `const [active, setActive] = useState(true)` — **mặc định TRUE** |
> | 🆕 State edit | 24 | `const [editId, setEditId] = useState(null)` — **null=Add, id=Edit** |
> | Load | 32-38 | `loadCourses`, `loadLevels`, `CourseService` |
> | 🆕 resetForm | 41-48 | **Hàm mới**: reset 5 state + `setEditId(null)` → về Add mode |
> | 🆕 handleSubmit | 51-94 | **Thay handleAddNew**: validation + if/else `editId` |
> | | 76-82 | `if (editId)` → `CourseService.update(editId, course)` → alert `"Updated"` |
> | | 84-92 | `else` → `CourseService.create(course)` → alert `"Created"` |
> | 🆕 handleEditClick | 97-104 | **Hàm mới**: `setEditId(course.id)` + điền 5 field vào form |
> | | | ⚠️ `setCredits(String(course.credits))` — số → string cho input |
> | Delete | 106-113 | `CourseService.deleteCourse` + `loadCourses` |
> | Form fields | ~125-165 | 4 TextBox + 1 DropList (đổi tên label + state) |
> | 🆕 Form CheckBox | ~168-175 | `<Form.Check type="checkbox" label="Active" checked={active}>` |
> | 🆕 Nút Submit | ~179 | `{editId ? "Update" : "Add New"}` — **text đổi theo mode** |
> | 🆕 Nút Cancel | ~182-185 | `{editId && <Button onClick={resetForm}>Cancel</Button>}` — **chỉ hiện khi Edit** |
> | Table thead | ~193 | Thêm cột `Active` |
> | Table tbody | ~205 | `{course.active ? "Yes" : "No"}` |
> | 🆕 Nút Edit | ~208-212 | `<Button variant="warning" onClick={handleEditClick}>Edit</Button>` — **nút mới trong Action** |
> | Modal body | ~229 | `deleteTarget?.courseName` |
>
> ---
>
> **CourseDetail.jsx** (đổi fields + thêm boolean):
> | Dòng | Thay đổi |
> |------|----------|
> | 4, 6, 9, 12, 15 | Đổi tên entity |
> | 24-33 | 5 field: `.courseName`, `.instructor`, `.level`, `.credits` |
> | 🆕 33 | `{course.active ? "Yes" : "No"}` — boolean hiển thị |

### File 1: `src/services/CourseService.js`

```javascript
import axios from "axios";

const API_URL = "http://localhost:8080/api/courses";

const getAll = () => axios.get(API_URL);
const getById = (id) => axios.get(`${API_URL}/${id}`);
const create = (course) => axios.post(API_URL, course);
const update = (id, course) => axios.put(`${API_URL}/${id}`, course); // ← MỚI: PUT
const deleteCourse = (id) => axios.delete(`${API_URL}/${id}`);
const getLevels = () => axios.get(`${API_URL}/levels`);

export default { getAll, getById, create, update, deleteCourse, getLevels };
```

### File 2: `src/App.jsx`

```jsx
import { Routes, Route } from "react-router-dom";
import CourseList from "./components/CourseList";
import CourseDetail from "./components/CourseDetail";

function App() {
  return (
    <Routes>
      <Route path="/" element={<CourseList />} />
      <Route path="/course/:id" element={<CourseDetail />} />
    </Routes>
  );
}

export default App;
```

### File 3: `src/components/CourseList.jsx`

> **Điểm khác so với BSM**: Có **Edit (PUT/Update)** + **CheckBox** + nút **Edit** trên table

```jsx
import { useState, useEffect } from "react";
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import CourseService from "../services/CourseService";

function CourseList() {
  const [courses, setCourses] = useState([]);
  const [levels, setLevels] = useState([]);
  const [courseName, setCourseName] = useState("");
  const [credits, setCredits] = useState("");
  const [instructor, setInstructor] = useState("");
  const [level, setLevel] = useState("");
  const [active, setActive] = useState(true); // ← Mặc định TRUE

  // ★ MỚI: State cho Edit mode
  const [editId, setEditId] = useState(null); // null = Add mode, có id = Edit mode

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() => {
    loadCourses();
    loadLevels();
  }, []);

  const loadCourses = () => {
    CourseService.getAll().then((res) => setCourses(res.data));
  };

  const loadLevels = () => {
    CourseService.getLevels().then((res) => setLevels(res.data));
  };

  // ★ Reset form về trạng thái ban đầu
  const resetForm = () => {
    setCourseName("");
    setCredits("");
    setInstructor("");
    setLevel("");
    setActive(true);
    setEditId(null); // ← Về lại Add mode
  };

  // ★ Xử lý cả Add và Update trong 1 hàm
  const handleSubmit = () => {
    // Validation (giống nhau cho cả Add và Update)
    if (!courseName.trim()) {
      alert("Course Name is required");
      return;
    }
    if (courseName.trim().length > 100) {
      alert("Course Name must be at most 100 characters");
      return;
    }

    if (!credits) {
      alert("Credits is required");
      return;
    }
    const creditsNum = Number(credits);
    if (isNaN(creditsNum) || creditsNum < 1 || creditsNum > 10) {
      alert("Credits must be between 1 and 10");
      return;
    }

    if (!instructor.trim()) {
      alert("Instructor is required");
      return;
    }
    if (instructor.trim().length > 80) {
      alert("Instructor must be at most 80 characters");
      return;
    }

    if (!level) {
      alert("Level is required");
      return;
    }

    const course = {
      courseName: courseName.trim(),
      credits: creditsNum,
      instructor: instructor.trim(),
      level,
      active,
    };

    if (editId) {
      // ★ MODE: UPDATE (PUT)
      CourseService.update(editId, course)
        .then(() => {
          alert("Updated Course successfully");
          loadCourses();
          resetForm(); // ← Reset form + về Add mode
        })
        .catch((err) => {
          alert(err.response?.data?.message || "Error updating course");
        });
    } else {
      // MODE: CREATE (POST)
      CourseService.create(course)
        .then(() => {
          alert("Created new Course successfully");
          loadCourses();
          resetForm();
        })
        .catch((err) => {
          alert(err.response?.data?.message || "Error creating course");
        });
    }
  };

  // ★ MỚI: Click Edit → điền data vào form
  const handleEditClick = (course) => {
    setEditId(course.id); // ← Chuyển sang Edit mode
    setCourseName(course.courseName);
    setCredits(String(course.credits)); // ← Số → String cho input
    setInstructor(course.instructor);
    setLevel(course.level);
    setActive(course.active);
  };

  const handleDeleteClick = (course) => {
    setDeleteTarget(course);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () => {
    CourseService.deleteCourse(deleteTarget.id).then(() => {
      alert("Deleted successfully");
      loadCourses();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () => {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    <Container>
      <h2 className="mt-3 mb-3">
        <b>Course Management</b>
      </h2>
      <Form>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Course Name:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={courseName}
              onChange={(e) => setCourseName(e.target.value)}
              maxLength={100}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Credits:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={credits}
              onChange={(e) => setCredits(e.target.value)}
              maxLength={2}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Instructor:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={instructor}
              onChange={(e) => setInstructor(e.target.value)}
              maxLength={80}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Level:
          </Form.Label>
          <Col sm={10}>
            <Form.Select
              value={level}
              onChange={(e) => setLevel(e.target.value)}
            >
              <option value="">-- Select --</option>
              {levels.map((l) => (
                <option key={l} value={l}>
                  {l}
                </option>
              ))}
            </Form.Select>
          </Col>
        </Form.Group>

        {/* CheckBox */}
        <Form.Group as={Row} className="mb-2">
          <Col sm={{ span: 10, offset: 2 }}>
            <Form.Check
              type="checkbox"
              label="Active"
              checked={active}
              onChange={(e) => setActive(e.target.checked)}
            />
          </Col>
        </Form.Group>

        {/* ★ Nút thay đổi text theo mode */}
        <Row className="mb-3">
          <Col sm={{ span: 10, offset: 2 }}>
            <Button variant="primary" onClick={handleSubmit}>
              {editId ? "Update" : "Add New"} {/* ← Đổi text */}
            </Button>
            {/* ★ Nút Cancel chỉ hiện khi đang Edit */}
            {editId && (
              <Button variant="secondary" className="ms-2" onClick={resetForm}>
                Cancel
              </Button>
            )}
          </Col>
        </Row>
      </Form>

      <h4>
        <b>Course List</b>
      </h4>
      <Table bordered hover>
        <thead>
          <tr>
            <th># No</th>
            <th>Course Name</th>
            <th>Level</th>
            <th>Instructor</th>
            <th>Credits</th>
            <th>Active</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {courses.map((course, index) => (
            <tr key={course.id}>
              <td>{String(index + 1).padStart(2, "0")}</td>
              <td>{course.courseName}</td>
              <td>{course.level}</td>
              <td>{course.instructor}</td>
              <td>{course.credits}</td>
              <td>{course.active ? "Yes" : "No"}</td>
              <td>
                {/* ★ MỚI: Nút Edit */}
                <Button
                  variant="warning"
                  size="sm"
                  onClick={() => handleEditClick(course)}
                >
                  Edit
                </Button>
                {" | "}
                <Button
                  variant="danger"
                  size="sm"
                  onClick={() => handleDeleteClick(course)}
                >
                  Delete
                </Button>
                {" | "}
                <Link to={`/course/${course.id}`}>View</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      {/* Modal xác nhận xóa */}
      <Modal show={showConfirm} onHide={handleDeleteClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to delete "{deleteTarget?.courseName}"?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleDeleteConfirm}>
            Yes
          </Button>
          <Button variant="secondary" onClick={handleDeleteClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default CourseList;
```

### File 4: `src/components/CourseDetail.jsx`

```jsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import CourseService from "../services/CourseService";

function CourseDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [course, setCourse] = useState(null);

  useEffect(() => {
    CourseService.getById(id).then((res) => setCourse(res.data));
  }, [id]);

  if (!course) return <Container className="mt-3">Loading...</Container>;

  return (
    <Container className="mt-3">
      <h2>
        <b>VIEW DETAILS</b>
      </h2>
      <div className="mt-4 ms-4">
        <p>
          <b>Course Name:</b> {course.courseName}
        </p>
        <p>
          <b>Instructor:</b> {course.instructor}
        </p>
        <p>
          <b>Level:</b> {course.level}
        </p>
        <p>
          <b>Credits:</b> {course.credits}
        </p>
        <p>
          <b>Active:</b> {course.active ? "Yes" : "No"}
        </p>
      </div>
      <Button variant="outline-primary" onClick={() => navigate("/")}>
        Back
      </Button>
    </Container>
  );
}

export default CourseDetail;
```

### Điểm cần lưu ý Đề 4:

| Feature                   | Chi tiết                                                  |
| ------------------------- | --------------------------------------------------------- |
| **Edit mode**             | State `editId`: `null` = Add, có giá trị = Edit           |
| **handleEditClick**       | Điền data cũ vào form (set tất cả state)                  |
| **handleSubmit**          | Kiểm tra `editId` → gọi `update()` hoặc `create()`        |
| **Nút thay đổi text**     | `{editId ? 'Update' : 'Add New'}`                         |
| **Nút Cancel**            | `{editId && <Button onClick={resetForm}>Cancel</Button>}` |
| **resetForm**             | Reset tất cả state + setEditId(null)                      |
| **credits Number→String** | `setCredits(String(course.credits))` khi edit             |
| **Service thêm update**   | `const update = (id, course) => axios.put(...)`           |

---

# BẢNG SO SÁNH 4 ĐỀ

|                  | Đề 1 (EMP) | Đề 2 (PRM)     | Đề 3 (STM)   | Đề 4 (CRM) |
| ---------------- | ---------- | -------------- | ------------ | ---------- |
| **Entity**       | Employee   | Product        | Student      | Course     |
| **TextBox**      | ✅         | ✅             | ✅           | ✅         |
| **DropList**     | ✅         | ✅             | ✅           | ✅         |
| **CheckBox**     | —          | ✅ inStock     | —            | ✅ active  |
| **Radio**        | —          | —              | ✅ gender    | —          |
| **TextArea**     | —          | ✅ description | —            | —          |
| **Date**         | —          | —              | ✅ birthDate | —          |
| **Image**        | —          | —              | ✅ avatarUrl | —          |
| **PUT (Update)** | —          | —              | —            | ✅         |
| **Nút Edit**     | —          | —              | —            | ✅         |
| **Cancel**       | —          | —              | —            | ✅         |
| **Độ khó**       | ⭐         | ⭐⭐           | ⭐⭐         | ⭐⭐⭐     |

---

# TEMPLATE NHANH - COPY PASTE

> Dùng khi gặp đề mới: copy template → đổi tên + field → xong.

## Template Service

```javascript
import axios from "axios";

const API_URL = "http://localhost:8080/___URL___";

const getAll = () => axios.get(API_URL);
const getById = (id) => axios.get(`${API_URL}/${id}`);
const create = (data) => axios.post(API_URL, data);
// const update = (id, data) => axios.put(`${API_URL}/${id}`, data)   // Nếu có PUT
const deleteItem = (id) => axios.delete(`${API_URL}/${id}`);
const getDropdownData = () => axios.get(`${API_URL}/___endpoint___`);

export default { getAll, getById, create, deleteItem, getDropdownData };
```

## Template App.jsx

```jsx
import { Routes, Route } from "react-router-dom";
import ___List from "./components/___List";
import ___Detail from "./components/___Detail";

function App() {
  return (
    <Routes>
      <Route path="/" element={<___List />} />
      <Route path="/___entity___/:id" element={<___Detail />} />
    </Routes>
  );
}

export default App;
```

## Template Detail

```jsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import ___Service from "../services/___Service";

function ___Detail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [item, setItem] = useState(null);

  useEffect(() => {
    ___Service.getById(id).then((res) => setItem(res.data));
  }, [id]);

  if (!item) return <Container className="mt-3">Loading...</Container>;

  return (
    <Container className="mt-3">
      <h2>
        <b>VIEW DETAILS</b>
      </h2>
      <div className="mt-4 ms-4">
        {/* ĐỔI CÁC FIELD THEO ĐỀ */}
        <p>
          <b>Field 1:</b> {item.field1}
        </p>
        <p>
          <b>Field 2:</b> {item.field2}
        </p>
      </div>
      <Button variant="outline-primary" onClick={() => navigate("/")}>
        Back
      </Button>
    </Container>
  );
}

export default ___Detail;
```

## Template List (khung cơ bản)

```jsx
import { useState, useEffect } from "react";
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import ___Service from "../services/___Service";

function ___List() {
  // === STATE ===
  const [items, setItems] = useState([]);
  const [dropdownData, setDropdownData] = useState([]);
  // Thêm state cho từng field...
  // const [field1, setField1] = useState('')
  // const [field2, setField2] = useState('')
  // const [boolField, setBoolField] = useState(false)     // checkbox
  // const [editId, setEditId] = useState(null)             // nếu có update

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  // === LOAD DATA ===
  useEffect(() => {
    loadItems();
    loadDropdown();
  }, []);

  const loadItems = () => {
    ___Service.getAll().then((res) => setItems(res.data));
  };

  const loadDropdown = () => {
    ___Service.getDropdownData().then((res) => setDropdownData(res.data));
  };

  const resetForm = () => {
    // Reset tất cả state field về giá trị ban đầu
    // setEditId(null)  // nếu có update
  };

  // === VALIDATION + SUBMIT ===
  const handleSubmit = () => {
    // 1. Validation...
    // 2. Tạo object
    // 3. Gọi API (create hoặc update)
    // 4. Alert + loadItems + resetForm
  };

  // === DELETE ===
  const handleDeleteClick = (item) => {
    setDeleteTarget(item);
    setShowConfirm(true);
  };
  const handleDeleteConfirm = () => {
    ___Service.deleteItem(deleteTarget.id).then(() => {
      alert("Deleted successfully");
      loadItems();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };
  const handleDeleteClose = () => {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  // === EDIT (nếu có) ===
  // const handleEditClick = (item) => { setEditId(item.id); /* set các field */ }

  return (
    <Container>
      <h2 className="mt-3 mb-3">
        <b>___ Management</b>
      </h2>

      {/* === FORM === */}
      <Form>
        {/* TextBox */}
        {/* <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">Field:</Form.Label>
          <Col sm={10}>
            <Form.Control type="text" value={field} onChange={(e) => setField(e.target.value)} />
          </Col>
        </Form.Group> */}

        {/* DropList */}
        {/* <Form.Select value={val} onChange={(e) => setVal(e.target.value)}>
          <option value="">-- Select --</option>
          {dropdownData.map(d => <option key={d} value={d}>{d}</option>)}
        </Form.Select> */}

        {/* CheckBox */}
        {/* <Form.Check type="checkbox" label="Label" checked={bool} onChange={(e) => setBool(e.target.checked)} /> */}

        {/* Radio */}
        {/* <Form.Check inline type="radio" label="A" name="grp" value="A" checked={val==='A'} onChange={(e) => setVal(e.target.value)} />
            <Form.Check inline type="radio" label="B" name="grp" value="B" checked={val==='B'} onChange={(e) => setVal(e.target.value)} /> */}

        {/* TextArea */}
        {/* <Form.Control as="textarea" rows={3} value={val} onChange={(e) => setVal(e.target.value)} /> */}

        {/* Date */}
        {/* <Form.Control type="date" value={val} onChange={(e) => setVal(e.target.value)} /> */}

        <Row className="mb-3">
          <Col sm={{ span: 10, offset: 2 }}>
            <Button variant="primary" onClick={handleSubmit}>
              {/* editId ? 'Update' : */} Add New
            </Button>
            {/* {editId && <Button variant="secondary" className="ms-2" onClick={resetForm}>Cancel</Button>} */}
          </Col>
        </Row>
      </Form>

      {/* === TABLE === */}
      <h4>
        <b>___ List</b>
      </h4>
      <Table bordered hover>
        <thead>
          <tr>
            <th># No</th>
            {/* Thêm th cho từng cột */}
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {items.map((item, index) => (
            <tr key={item.id}>
              <td>{String(index + 1).padStart(2, "0")}</td>
              {/* Thêm td cho từng field */}
              <td>
                {/* {<Button variant="warning" size="sm" onClick={() => handleEditClick(item)}>Edit</Button>} */}
                {/* {' | '} */}
                <Button
                  variant="danger"
                  size="sm"
                  onClick={() => handleDeleteClick(item)}
                >
                  Delete
                </Button>
                {" | "}
                <Link to={`/___entity___/${item.id}`}>View</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      {/* === MODAL === */}
      <Modal show={showConfirm} onHide={handleDeleteClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to delete "{deleteTarget?.___field___}"?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleDeleteConfirm}>
            Yes
          </Button>
          <Button variant="secondary" onClick={handleDeleteClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default ___List;
```

---

# CÁC FILE KHÔNG ĐỔI (COPY NGUYÊN)

## `main.jsx` (giữ nguyên mọi đề)

```jsx
import React from "react";
import { createRoot } from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import App from "./App.jsx";
import { BrowserRouter } from "react-router-dom";

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>,
);
```

## `jsconfig.json` (giữ nguyên mọi đề)

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

## `vite.config.js` (giữ nguyên mọi đề)

```javascript
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
  },
});
```

---

> **Lời khuyên**: Làm lần lượt Đề 1 → 2 → 3 → 4. Mỗi đề thêm 1-2 component mới. Sau khi làm hết 4 đề, bạn sẽ tự tin xử lý mọi dạng đề PE.
