# Đề Mẫu Luyện Tập PE - SBA301 (React + Spring Boot)

> 5 đề mẫu đầy đủ với lời giải chi tiết, mỗi đề có component khác nhau để luyện tập toàn diện.

---

## Mục Lục

| Đề                                    | Tên                       | Component đặc biệt                                        | Trang                      |
| ------------------------------------- | ------------------------- | --------------------------------------------------------- | -------------------------- |
| [Đề 1](#đề-1-employee-management-emp) | Employee Management (EMP) | TextBox, DropList, Table, Modal, Link                     | Cơ bản (giống BSM)         |
| [Đề 2](#đề-2-product-management-prm)  | Product Management (PRM)  | TextBox, Number, CheckBox, TextArea, DropList             | Có Checkbox + TextArea     |
| [Đề 3](#đề-3-student-management-stm)  | Student Management (STM)  | TextBox, Date, Radio Button, DropList, Image              | Có Radio + Date + Image    |
| [Đề 4](#đề-4-course-management-crm)   | Course Management (CRM)   | TextBox, Number, CheckBox (nhiều), DropList, PUT (Update) | Có Update + Multi-Checkbox |
| [Đề 5](#đề-5-event-management-evm)    | Event Management (EVM)    | Full CRUD, Search, Sort, Pagination, đầy đủ control form  | Đề tổng hợp nâng cao       |

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

> Quy ước: phần <span style="color:red">màu đỏ</span> là token có thể thay đổi theo đề được giao (entity, field, endpoint, route, label, alert, validation rule, text button).

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

<pre><code>import axios from "axios";

const API_URL = <span style="color:red">"http://localhost:8080/api/employees"</span>; // Đổi URL: /myapp/shops -&gt; /api/employees

const getAll = () =&gt; axios.get(API_URL);
const getById = (id) =&gt; axios.get(`${API_URL}/${id}`);
const create = (<span style="color:red">employee</span>) =&gt; axios.post(API_URL, <span style="color:red">employee</span>); // Đổi tham số: shop -&gt; employee
const <span style="color:red">deleteEmployee</span> = (id) =&gt; axios.delete(`${API_URL}/${id}`); // Đổi tên hàm: deleteShop -&gt; deleteEmployee
const <span style="color:red">getDepartments</span> = () =&gt; axios.get(`${API_URL}/<span style="color:red">departments</span>`); // Đổi endpoint: /types -&gt; /departments

export default { getAll, getById, create, <span style="color:red">deleteEmployee</span>, <span style="color:red">getDepartments</span> };</code></pre>

### File 2: `src/App.jsx`

<pre><code>import { Routes, Route } from "react-router-dom";
import <span style="color:red">EmployeeList</span> from "./components/<span style="color:red">EmployeeList</span>"; // Đổi component: ShopList -&gt; EmployeeList
import <span style="color:red">EmployeeDetail</span> from "./components/<span style="color:red">EmployeeDetail</span>"; // Đổi component: ShopDetail -&gt; EmployeeDetail

function App() {
  return (
    &lt;Routes&gt;
      &lt;Route path="/" element={&lt;<span style="color:red">EmployeeList</span> /&gt;} /&gt;
      &lt;Route path="<span style="color:red">/employee/:id</span>" element={&lt;<span style="color:red">EmployeeDetail</span> /&gt;} /&gt; {/* Đổi route: /shop/:id -&gt; /employee/:id */}
    &lt;/Routes&gt;
  );
}

export default App;</code></pre>

### File 3: `src/components/EmployeeList.jsx`

<pre><code>import { useState, useEffect } from &quot;react&quot;;
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from &quot;react-bootstrap&quot;;
import { Link } from &quot;react-router-dom&quot;;
import <span style="color:red">EmployeeService</span> from &quot;../services/<span style="color:red">EmployeeService</span>&quot;; 

function <span style="color:red">EmployeeList</span>() {
  const [<span style="color:red">employees</span>, <span style="color:red">setEmployees</span>] = useState([]); 
  const [<span style="color:red">departments</span>, <span style="color:red">setDepartments</span>] = useState([]); 
  const [<span style="color:red">fullName</span>, <span style="color:red">setFullName</span>] = useState(&quot;&quot;); 
  const [<span style="color:red">age</span>, <span style="color:red">setAge</span>] = useState(&quot;&quot;); 
  const [<span style="color:red">email</span>, <span style="color:red">setEmail</span>] = useState(&quot;&quot;); 
  const [<span style="color:red">department</span>, <span style="color:red">setDepartment</span>] = useState(&quot;&quot;); 

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">loadEmployees</span>();
    <span style="color:red">loadDepartments</span>();
  }, []);

  const <span style="color:red">loadEmployees</span> = () =&gt; {
    <span style="color:red">EmployeeService</span>.getAll().then((res) =&gt; <span style="color:red">setEmployees</span>(res.data));
  };

  const <span style="color:red">loadDepartments</span> = () =&gt; {
    <span style="color:red">EmployeeService</span>.<span style="color:red">getDepartments</span>().then((res) =&gt; <span style="color:red">setDepartments</span>(res.data));
  };

  const handleAddNew = () =&gt; {
    
    if (!<span style="color:red">fullName</span>.trim()) {
      alert(&quot;<span style="color:red">Full Name is required</span>&quot;);
      return;
    }
    if (<span style="color:red">fullName</span>.trim().length &gt; 80) {
      alert(&quot;<span style="color:red">Full Name must be at most 80 characters</span>&quot;);
      return;
    }
    if (!<span style="color:red">age</span>) {
      alert(&quot;<span style="color:red">Age is required</span>&quot;);
      return;
    }
    const <span style="color:red">ageNum</span> = Number(<span style="color:red">age</span>);
    if (isNaN(<span style="color:red">ageNum</span>) || <span style="color:red">ageNum</span> &lt;= 18 || <span style="color:red">ageNum</span> &gt;= 65) {
      alert(&quot;<span style="color:red">Age must be greater than 18 and less than 65</span>&quot;);
      return;
    }
    if (!<span style="color:red">email</span>.trim()) {
      alert(&quot;<span style="color:red">Email is required</span>&quot;);
      return;
    }
    if (<span style="color:red">email</span>.trim().length &gt; 150) {
      alert(&quot;<span style="color:red">Email must be at most 150 characters</span>&quot;);
      return;
    }
    if (!<span style="color:red">department</span>) {
      alert(&quot;<span style="color:red">Department is required</span>&quot;);
      return;
    }

    const <span style="color:red">employee</span> = {
      <span style="color:red">fullName</span>: <span style="color:red">fullName</span>.trim(),
      <span style="color:red">age</span>: <span style="color:red">ageNum</span>,
      <span style="color:red">email</span>: <span style="color:red">email</span>.trim(),
      <span style="color:red">department</span>,
    };

    <span style="color:red">EmployeeService</span>.create(<span style="color:red">employee</span>)
      .then(() =&gt; {
        alert(&quot;<span style="color:red">Created new Employee successfully</span>&quot;);
        <span style="color:red">loadEmployees</span>();
        <span style="color:red">setFullName</span>(&quot;&quot;);
        <span style="color:red">setAge</span>(&quot;&quot;);
        <span style="color:red">setEmail</span>(&quot;&quot;);
        <span style="color:red">setDepartment</span>(&quot;&quot;);
      })
      .catch((err) =&gt; {
        alert(err.response?.data?.message || &quot;<span style="color:red">Error creating employee</span>&quot;);
      });
  };

  const handleDeleteClick = (emp) =&gt; {
    setDeleteTarget(emp);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () =&gt; {
    <span style="color:red">EmployeeService</span>.<span style="color:red">deleteEmployee</span>(deleteTarget.id).then(() =&gt; {
      alert(&quot;
      <span style="color:red">loadEmployees</span>();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () =&gt; {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    &lt;Container&gt;
      &lt;h2 className=&quot;mt-3 mb-3&quot;&gt;
        &lt;b&gt;<span style="color:red">Employee Management</span>&lt;/b&gt; {/* Đổi tiêu đề */}
      &lt;/h2&gt;
      &lt;Form&gt;
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Full Name:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">fullName</span>}
              onChange={(e) =&gt; <span style="color:red">setFullName</span>(e.target.value)}
              maxLength={80}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Age:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">age</span>}
              onChange={(e) =&gt; <span style="color:red">setAge</span>(e.target.value)}
              maxLength={2}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Email:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">email</span>}
              onChange={(e) =&gt; <span style="color:red">setEmail</span>(e.target.value)}
              maxLength={150}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Department:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Select
              value={<span style="color:red">department</span>}
              onChange={(e) =&gt; <span style="color:red">setDepartment</span>(e.target.value)}
            &gt;
              &lt;option value=&quot;&quot;&gt;/option&gt;
              {<span style="color:red">departments</span>.map((d) =&gt; (
                &lt;option key={d} value={d}&gt;
                  {d}
                &lt;/option&gt;
              ))}
            &lt;/Form.Select&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;
        &lt;Row className=&quot;mb-3&quot;&gt;
          &lt;Col sm={{ span: 10, offset: 2 }}&gt;
            &lt;Button variant=&quot;primary&quot; onClick={handleAddNew}&gt;
              Add New
            &lt;/Button&gt;
          &lt;/Col&gt;
        &lt;/Row&gt;
      &lt;/Form&gt;

      &lt;h4&gt;
        &lt;b&gt;<span style="color:red">Employee List</span>&lt;/b&gt; {/* Đổi subtitle */}
      &lt;/h4&gt;
      &lt;Table bordered hover&gt;
        &lt;thead&gt;
          &lt;tr&gt;
            &lt;th&gt;# No&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Full Name</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Department</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Email</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Age</span>&lt;/th&gt;
            &lt;th&gt;Action&lt;/th&gt;
          &lt;/tr&gt;
        &lt;/thead&gt;
        &lt;tbody&gt;
          {<span style="color:red">employees</span>.map((emp, index) =&gt; (
            &lt;tr key={emp.id}&gt;
              &lt;td&gt;{String(index + 1).padStart(2, &quot;0&quot;)}&lt;/td&gt;
              &lt;td&gt;{emp.<span style="color:red">fullName</span>}&lt;/td&gt;
              &lt;td&gt;{emp.<span style="color:red">department</span>}&lt;/td&gt;
              &lt;td&gt;{emp.<span style="color:red">email</span>}&lt;/td&gt;
              &lt;td&gt;{emp.<span style="color:red">age</span>}&lt;/td&gt;
              &lt;td&gt;
                &lt;Button
                  variant=&quot;danger&quot;
                  size=&quot;sm&quot;
                  onClick={() =&gt; handleDeleteClick(emp)}
                &gt;
                  Delete
                &lt;/Button&gt;
                {&quot; | &quot;}
                &lt;Link to={`<span style="color:red">/employee/</span>${emp.id}`}&gt;<span style="color:red">View</span>&lt;/Link&gt;{&quot; &quot;}
                {/* Đổi link: /shop/:id -&gt; <span style="color:red">/employee/</span>:id */}
              &lt;/td&gt;
            &lt;/tr&gt;
          ))}
        &lt;/tbody&gt;
      &lt;/Table&gt;

      &lt;Modal show={showConfirm} onHide={handleDeleteClose} centered&gt;
        &lt;Modal.Header closeButton&gt;
          &lt;Modal.Title&gt;<span style="color:red">Confirmation</span>&lt;/Modal.Title&gt;
        &lt;/Modal.Header&gt;
        &lt;Modal.Body&gt;
          Are you sure you want to delete &quot;{deleteTarget?.<span style="color:red">fullName</span>}&quot;?
        &lt;/Modal.Body&gt;
        &lt;Modal.Footer&gt;
          &lt;Button variant=&quot;primary&quot; onClick={handleDeleteConfirm}&gt;
            <span style="color:red">Yes</span>
          &lt;/Button&gt;
          &lt;Button variant=&quot;secondary&quot; onClick={handleDeleteClose}&gt;
            <span style="color:red">Close</span>
          &lt;/Button&gt;
        &lt;/Modal.Footer&gt;
      &lt;/Modal&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">EmployeeList</span>;
</code></pre>

### File 4: `src/components/EmployeeDetail.jsx`

<pre><code>import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import <span style="color:red">EmployeeService</span> from "../services/<span style="color:red">EmployeeService</span>"; // Đổi service: ShopService -&gt; EmployeeService

function <span style="color:red">EmployeeDetail</span>() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [<span style="color:red">employee, setEmployee</span>] = useState(null); // Đổi state: shop -&gt; employee

  useEffect(() =&gt; {
    <span style="color:red">EmployeeService</span>.getById(id).then((res) =&gt; <span style="color:red">setEmployee</span>(res.data)); // Đổi API gọi detail theo Employee
  }, [id]);

  if (!<span style="color:red">employee</span>) return &lt;Container className="mt-3"&gt;<span style="color:red">Loading...</span>&lt;/Container&gt;;

  return (
    &lt;Container className="mt-3"&gt;
      &lt;h2&gt;
        &lt;b&gt;<span style="color:red">VIEW DETAILS</span>&lt;/b&gt;
      &lt;/h2&gt;
      &lt;div className="mt-4 ms-4"&gt;
        &lt;p&gt;
          &lt;b&gt;<span style="color:red">Full Name</span>:&lt;/b&gt; {employee.<span style="color:red">fullName</span>} {/* Đổi field: name -&gt; fullName */}
        &lt;/p&gt;
        &lt;p&gt;
          &lt;b&gt;<span style="color:red">Email</span>:&lt;/b&gt; {employee.<span style="color:red">email</span>} {/* Đổi field: owner -&gt; email */}
        &lt;/p&gt;
        &lt;p&gt;
          &lt;b&gt;<span style="color:red">Department</span>:&lt;/b&gt; {employee.<span style="color:red">department</span>} {/* Đổi field: type -&gt; department */}
        &lt;/p&gt;
        &lt;p&gt;
          &lt;b&gt;<span style="color:red">Age</span>:&lt;/b&gt; {employee.<span style="color:red">age</span>} {/* Đổi field: openTime -&gt; age */}
        &lt;/p&gt;
      &lt;/div&gt;
      &lt;Button variant="outline-primary" onClick={() =&gt; navigate("/")}&gt;
        <span style="color:red">Back</span>
      &lt;/Button&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">EmployeeDetail</span>;</code></pre>

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

> Quy ước: phần <span style="color:red">màu đỏ</span> là token có thể thay đổi theo đề được giao (entity, field, endpoint, route, label, alert, validation rule, text button).

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

<pre><code>import axios from "axios";

const API_URL = <span style="color:red">"http://localhost:8080/api/products"</span>;

const getAll = () =&gt; axios.get(API_URL);
const getById = (id) =&gt; axios.get(`${API_URL}/${id}`);
const create = (<span style="color:red">product</span>) =&gt; axios.post(API_URL, <span style="color:red">product</span>);
const <span style="color:red">deleteProduct</span> = (id) =&gt; axios.delete(`${API_URL}/${id}`);
const <span style="color:red">getCategories</span> = () =&gt; axios.get(`${API_URL}/<span style="color:red">categories</span>`);

export default { getAll, getById, create, <span style="color:red">deleteProduct</span>, <span style="color:red">getCategories</span> };</code></pre>

### File 2: `src/App.jsx`

<pre><code>import { Routes, Route } from "react-router-dom";
import <span style="color:red">ProductList</span> from "./components/<span style="color:red">ProductList</span>";
import <span style="color:red">ProductDetail</span> from "./components/<span style="color:red">ProductDetail</span>";

function App() {
  return (
    &lt;Routes&gt;
      &lt;Route path="/" element={&lt;<span style="color:red">ProductList</span> /&gt;} /&gt;
      &lt;Route path="<span style="color:red">/product/:id</span>" element={&lt;<span style="color:red">ProductDetail</span> /&gt;} /&gt;
    &lt;/Routes&gt;
  );
}

export default App;</code></pre>

### File 3: `src/components/ProductList.jsx`

> **Điểm khác so với BSM**: Có **CheckBox** (`inStock`) và **TextArea** (`description`)

<pre><code>import { useState, useEffect } from &quot;react&quot;;
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from &quot;react-bootstrap&quot;;
import { Link } from &quot;react-router-dom&quot;;
import <span style="color:red">ProductService</span> from &quot;../services/<span style="color:red">ProductService</span>&quot;;

function <span style="color:red">ProductList</span>() {
  const [<span style="color:red">products</span>, <span style="color:red">setProducts</span>] = useState([]);
  const [<span style="color:red">categories</span>, <span style="color:red">setCategories</span>] = useState([]);
  const [<span style="color:red">productName</span>, <span style="color:red">setProductName</span>] = useState(&quot;&quot;);
  const [<span style="color:red">price</span>, <span style="color:red">setPrice</span>] = useState(&quot;&quot;);
  const [<span style="color:red">description</span>, <span style="color:red">setDescription</span>] = useState(&quot;&quot;); 
  const [<span style="color:red">inStock</span>, <span style="color:red">setInStock</span>] = useState(false); 
  const [<span style="color:red">category</span>, <span style="color:red">setCategory</span>] = useState(&quot;&quot;);

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">loadProducts</span>();
    <span style="color:red">loadCategories</span>();
  }, []);

  const <span style="color:red">loadProducts</span> = () =&gt; {
    <span style="color:red">ProductService</span>.getAll().then((res) =&gt; <span style="color:red">setProducts</span>(res.data));
  };

  const <span style="color:red">loadCategories</span> = () =&gt; {
    <span style="color:red">ProductService</span>.<span style="color:red">getCategories</span>().then((res) =&gt; <span style="color:red">setCategories</span>(res.data));
  };

  const handleAddNew = () =&gt; {
    // Validation
    if (!<span style="color:red">productName</span>.trim()) {
      alert(&quot;<span style="color:red">Product Name is required</span>&quot;);
      return;
    }
    if (<span style="color:red">productName</span>.trim().length &gt; 100) {
      alert(&quot;<span style="color:red">Product Name must be at most 100 characters</span>&quot;);
      return;
    }

    if (!<span style="color:red">price</span>) {
      alert(&quot;<span style="color:red">Price is required</span>&quot;);
      return;
    }
    const <span style="color:red">priceNum</span> = Number(<span style="color:red">price</span>);
    if (isNaN(<span style="color:red">priceNum</span>) || <span style="color:red">priceNum</span> &lt;= 0 || <span style="color:red">priceNum</span> &gt; 99999) {
      alert(&quot;<span style="color:red">Price must be greater than 0 and at most 99999</span>&quot;);
      return;
    }

    // Description: KHÔNG bắt buộc, chỉ check maxLength
    if (<span style="color:red">description</span>.trim().length &gt; 500) {
      alert(&quot;<span style="color:red">Description must be at most 500 characters</span>&quot;);
      return;
    }

    if (!<span style="color:red">category</span>) {
      alert(&quot;<span style="color:red">Category is required</span>&quot;);
      return;
    }

    

    const <span style="color:red">product</span> = {
      <span style="color:red">productName</span>: <span style="color:red">productName</span>.trim(),
      <span style="color:red">price</span>: <span style="color:red">priceNum</span>,
      <span style="color:red">description</span>: <span style="color:red">description</span>.trim(),
      <span style="color:red">inStock</span>, 
      <span style="color:red">category</span>,
    };

    <span style="color:red">ProductService</span>.create(<span style="color:red">product</span>)
      .then(() =&gt; {
        alert(&quot;<span style="color:red">Created new Product successfully</span>&quot;);
        <span style="color:red">loadProducts</span>();
        <span style="color:red">setProductName</span>(&quot;&quot;);
        <span style="color:red">setPrice</span>(&quot;&quot;);
        <span style="color:red">setDescription</span>(&quot;&quot;);
        <span style="color:red">setInStock</span>(false); 
        <span style="color:red">setCategory</span>(&quot;&quot;);
      })
      .catch((err) =&gt; {
        alert(err.response?.data?.message || &quot;<span style="color:red">Error creating product</span>&quot;);
      });
  };

  const handleDeleteClick = (product) =&gt; {
    setDeleteTarget(product);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () =&gt; {
    <span style="color:red">ProductService</span>.<span style="color:red">deleteProduct</span>(deleteTarget.id).then(() =&gt; {
      alert(&quot;
      <span style="color:red">loadProducts</span>();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () =&gt; {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    &lt;Container&gt;
      &lt;h2 className=&quot;mt-3 mb-3&quot;&gt;
        &lt;b&gt;<span style="color:red">Product Management</span>&lt;/b&gt;
      &lt;/h2&gt;
      &lt;Form&gt;
        {/* TextBox - Product Name */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Product Name:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">productName</span>}
              onChange={(e) =&gt; <span style="color:red">setProductName</span>(e.target.value)}
              maxLength={100}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* TextBox - Price (nhập số) */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Price:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">price</span>}
              onChange={(e) =&gt; <span style="color:red">setPrice</span>(e.target.value)}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* ✅ TextArea - Description */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Description:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              as=&quot;textarea&quot; 
              rows={3}
              value={<span style="color:red">description</span>}
              onChange={(e) =&gt; <span style="color:red">setDescription</span>(e.target.value)}
              maxLength={500}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* ✅ CheckBox - In Stock */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Col sm={{ span: 10, offset: 2 }}&gt;
            &lt;Form.Check
              type=&quot;checkbox&quot;
              label=&quot;<span style="color:red">In Stock</span>&quot;
              checked={<span style="color:red">inStock</span>} 
              onChange={(e) =&gt; <span style="color:red">setInStock</span>(e.target.checked)} 
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* DropList - Category */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Category:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Select
              value={<span style="color:red">category</span>}
              onChange={(e) =&gt; <span style="color:red">setCategory</span>(e.target.value)}
            &gt;
              &lt;option value=&quot;&quot;&gt;<span style="color:red">-- Select --</span>&lt;/option&gt;
              {<span style="color:red">categories</span>.map((c) =&gt; (
                &lt;option key={c} value={c}&gt;
                  {c}
                &lt;/option&gt;
              ))}
            &lt;/Form.Select&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        &lt;Row className=&quot;mb-3&quot;&gt;
          &lt;Col sm={{ span: 10, offset: 2 }}&gt;
            &lt;Button variant=&quot;primary&quot; onClick={handleAddNew}&gt;
              Add New
            &lt;/Button&gt;
          &lt;/Col&gt;
        &lt;/Row&gt;
      &lt;/Form&gt;

      &lt;h4&gt;
        &lt;b&gt;<span style="color:red">Product List</span>&lt;/b&gt;
      &lt;/h4&gt;
      &lt;Table bordered hover&gt;
        &lt;thead&gt;
          &lt;tr&gt;
            &lt;th&gt;# No&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Product Name</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Category</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Price</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">In Stock</span>&lt;/th&gt;
            &lt;th&gt;Action&lt;/th&gt;
          &lt;/tr&gt;
        &lt;/thead&gt;
        &lt;tbody&gt;
          {<span style="color:red">products</span>.map((product, index) =&gt; (
            &lt;tr key={product.id}&gt;
              &lt;td&gt;{String(index + 1).padStart(2, &quot;0&quot;)}&lt;/td&gt;
              &lt;td&gt;{product.<span style="color:red">productName</span>}&lt;/td&gt;
              &lt;td&gt;{product.<span style="color:red">category</span>}&lt;/td&gt;
              &lt;td&gt;{product.<span style="color:red">price</span>}&lt;/td&gt;
              &lt;td&gt;{product.<span style="color:red">inStock</span> ? &quot;<span style="color:red">Yes</span>&quot; : &quot;<span style="color:red">No</span>&quot;}&lt;/td&gt;{&quot; &quot;}
              {/* ✅ Boolean hiển thị */}
              &lt;td&gt;
                &lt;Button
                  variant=&quot;danger&quot;
                  size=&quot;sm&quot;
                  onClick={() =&gt; handleDeleteClick(product)}
                &gt;
                  Delete
                &lt;/Button&gt;
                {&quot; | &quot;}
                &lt;Link to={`<span style="color:red">/product/</span>${product.id}`}&gt;<span style="color:red">View</span>&lt;/Link&gt;
              &lt;/td&gt;
            &lt;/tr&gt;
          ))}
        &lt;/tbody&gt;
      &lt;/Table&gt;

      {/* Modal xác nhận xóa */}
      &lt;Modal show={showConfirm} onHide={handleDeleteClose} centered&gt;
        &lt;Modal.Header closeButton&gt;
          &lt;Modal.Title&gt;<span style="color:red">Confirmation</span>&lt;/Modal.Title&gt;
        &lt;/Modal.Header&gt;
        &lt;Modal.Body&gt;
          Are you sure you want to delete &quot;{deleteTarget?.<span style="color:red">productName</span>}&quot;?
        &lt;/Modal.Body&gt;
        &lt;Modal.Footer&gt;
          &lt;Button variant=&quot;primary&quot; onClick={handleDeleteConfirm}&gt;
            <span style="color:red">Yes</span>
          &lt;/Button&gt;
          &lt;Button variant=&quot;secondary&quot; onClick={handleDeleteClose}&gt;
            <span style="color:red">Close</span>
          &lt;/Button&gt;
        &lt;/Modal.Footer&gt;
      &lt;/Modal&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">ProductList</span>;
</code></pre>

### File 4: `src/components/ProductDetail.jsx`

<pre><code>import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import <span style="color:red">ProductService</span> from "../services/<span style="color:red">ProductService</span>";

function <span style="color:red">ProductDetail</span>() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [<span style="color:red">product, setProduct</span>] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">ProductService</span>.getById(id).then((res) =&gt; <span style="color:red">setProduct</span>(res.data));
  }, [id]);

  if (!<span style="color:red">product</span>) return &lt;Container className="mt-3"&gt;<span style="color:red">Loading...</span>&lt;/Container&gt;;

  return (
    &lt;Container className="mt-3"&gt;
      &lt;h2&gt;
        &lt;b&gt;<span style="color:red">VIEW DETAILS</span>&lt;/b&gt;
      &lt;/h2&gt;
      &lt;div className="mt-4 ms-4"&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Product Name</span>:&lt;/b&gt; {product.<span style="color:red">productName</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Category</span>:&lt;/b&gt; {product.<span style="color:red">category</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Price</span>:&lt;/b&gt; {product.<span style="color:red">price</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">In Stock</span>:&lt;/b&gt; {product.<span style="color:red">inStock</span> ? "<span style="color:red">Yes</span>" : "<span style="color:red">No</span>"}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Description</span>:&lt;/b&gt; {product.<span style="color:red">description</span>}&lt;/p&gt;
      &lt;/div&gt;
      &lt;Button variant="outline-primary" onClick={() =&gt; navigate("/")}&gt;
        <span style="color:red">Back</span>
      &lt;/Button&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">ProductDetail</span>;</code></pre>

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

> Quy ước: phần <span style="color:red">màu đỏ</span> là token có thể thay đổi theo đề được giao (entity, field, endpoint, route, label, alert, validation rule, text button).

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

<pre><code>import axios from "axios";

const API_URL = <span style="color:red">"http://localhost:8080/api/students"</span>;

const getAll = () =&gt; axios.get(API_URL);
const getById = (id) =&gt; axios.get(`${API_URL}/${id}`);
const create = (<span style="color:red">student</span>) =&gt; axios.post(API_URL, <span style="color:red">student</span>);
const <span style="color:red">deleteStudent</span> = (id) =&gt; axios.delete(`${API_URL}/${id}`);
const <span style="color:red">getMajors</span> = () =&gt; axios.get(`${API_URL}/<span style="color:red">majors</span>`);

export default { getAll, getById, create, <span style="color:red">deleteStudent</span>, <span style="color:red">getMajors</span> };</code></pre>

### File 2: `src/App.jsx`

<pre><code>import { Routes, Route } from "react-router-dom";
import <span style="color:red">StudentList</span> from "./components/<span style="color:red">StudentList</span>";
import <span style="color:red">StudentDetail</span> from "./components/<span style="color:red">StudentDetail</span>";

function App() {
  return (
    &lt;Routes&gt;
      &lt;Route path="/" element={&lt;<span style="color:red">StudentList</span> /&gt;} /&gt;
      &lt;Route path="<span style="color:red">/student/:id</span>" element={&lt;<span style="color:red">StudentDetail</span> /&gt;} /&gt;
    &lt;/Routes&gt;
  );
}

export default App;</code></pre>

### File 3: `src/components/StudentList.jsx`

> **Điểm khác so với BSM**: Có **Radio Button** (`gender`), **Date Picker** (`birthDate`), **Image** (`avatarUrl`)

<pre><code>import { useState, useEffect } from &quot;react&quot;;
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from &quot;react-bootstrap&quot;;
import { Link } from &quot;react-router-dom&quot;;
import <span style="color:red">StudentService</span> from &quot;../services/<span style="color:red">StudentService</span>&quot;;

function <span style="color:red">StudentList</span>() {
  const [<span style="color:red">students</span>, <span style="color:red">setStudents</span>] = useState([]);
  const [<span style="color:red">majors</span>, <span style="color:red">setMajors</span>] = useState([]);
  const [<span style="color:red">studentName</span>, <span style="color:red">setStudentName</span>] = useState(&quot;&quot;);
  const [<span style="color:red">birthDate</span>, <span style="color:red">setBirthDate</span>] = useState(&quot;&quot;); 
  const [<span style="color:red">gender</span>, <span style="color:red">setGender</span>] = useState(&quot;&quot;); 
  const [<span style="color:red">major</span>, <span style="color:red">setMajor</span>] = useState(&quot;&quot;);
  const [<span style="color:red">avatarUrl</span>, <span style="color:red">setAvatarUrl</span>] = useState(&quot;&quot;); 

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">loadStudents</span>();
    <span style="color:red">loadMajors</span>();
  }, []);

  const <span style="color:red">loadStudents</span> = () =&gt; {
    <span style="color:red">StudentService</span>.getAll().then((res) =&gt; <span style="color:red">setStudents</span>(res.data));
  };

  const <span style="color:red">loadMajors</span> = () =&gt; {
    <span style="color:red">StudentService</span>.<span style="color:red">getMajors</span>().then((res) =&gt; <span style="color:red">setMajors</span>(res.data));
  };

  const handleAddNew = () =&gt; {
    // Validation
    if (!<span style="color:red">studentName</span>.trim()) {
      alert(&quot;<span style="color:red">Student Name is required</span>&quot;);
      return;
    }
    if (<span style="color:red">studentName</span>.trim().length &gt; 100) {
      alert(&quot;<span style="color:red">Student Name must be at most 100 characters</span>&quot;);
      return;
    }

    
    if (!<span style="color:red">birthDate</span>) {
      alert(&quot;<span style="color:red">Birth Date is required</span>&quot;);
      return;
    }
    const selectedDate = new Date(<span style="color:red">birthDate</span>);
    const today = new Date();
    today.setHours(0, 0, 0, 0); // Reset giờ để so sánh chính xác
    if (selectedDate &gt; today) {
      alert(&quot;<span style="color:red">Birth Date cannot be in the future</span>&quot;);
      return;
    }

    
    if (!<span style="color:red">gender</span>) {
      alert(&quot;<span style="color:red">Gender is required</span>&quot;);
      return;
    }

    if (!<span style="color:red">major</span>) {
      alert(&quot;<span style="color:red">Major is required</span>&quot;);
      return;
    }

    // <span style="color:red">avatarUrl</span>: KHÔNG bắt buộc

    const <span style="color:red">student</span> = {
      <span style="color:red">studentName</span>: <span style="color:red">studentName</span>.trim(),
      <span style="color:red">birthDate</span>, // Format: &quot;2000-05-15&quot; (yyyy-MM-dd)
      <span style="color:red">gender</span>, // &quot;Male&quot; hoặc &quot;Female&quot;
      <span style="color:red">major</span>,
      <span style="color:red">avatarUrl</span>: <span style="color:red">avatarUrl</span>.trim(),
    };

    <span style="color:red">StudentService</span>.create(<span style="color:red">student</span>)
      .then(() =&gt; {
        alert(&quot;<span style="color:red">Created new Student successfully</span>&quot;);
        <span style="color:red">loadStudents</span>();
        <span style="color:red">setStudentName</span>(&quot;&quot;);
        <span style="color:red">setBirthDate</span>(&quot;&quot;);
        <span style="color:red">setGender</span>(&quot;&quot;); 
        <span style="color:red">setMajor</span>(&quot;&quot;);
        <span style="color:red">setAvatarUrl</span>(&quot;&quot;);
      })
      .catch((err) =&gt; {
        alert(err.response?.data?.message || &quot;<span style="color:red">Error creating student</span>&quot;);
      });
  };

  const handleDeleteClick = (student) =&gt; {
    setDeleteTarget(student);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () =&gt; {
    <span style="color:red">StudentService</span>.<span style="color:red">deleteStudent</span>(deleteTarget.id).then(() =&gt; {
      alert(&quot;
      <span style="color:red">loadStudents</span>();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () =&gt; {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    &lt;Container&gt;
      &lt;h2 className=&quot;mt-3 mb-3&quot;&gt;
        &lt;b&gt;<span style="color:red">Student Management</span>&lt;/b&gt;
      &lt;/h2&gt;
      &lt;Form&gt;
        {/* TextBox - Student Name */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Student Name:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">studentName</span>}
              onChange={(e) =&gt; <span style="color:red">setStudentName</span>(e.target.value)}
              maxLength={100}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* ✅ Date Picker - Birth Date */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Birth Date:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;date&quot; 
              value={<span style="color:red">birthDate</span>}
              onChange={(e) =&gt; <span style="color:red">setBirthDate</span>(e.target.value)}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* ✅ Radio Button - Gender */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Gender:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Check
              inline 
              type=&quot;radio&quot; 
              label=&quot;Male&quot;
              name=&quot;<span style="color:red">gender</span>&quot; 
              value=&quot;Male&quot;
              checked={<span style="color:red">gender</span> === &quot;Male&quot;} 
              onChange={(e) =&gt; <span style="color:red">setGender</span>(e.target.value)} 
            /&gt;
            &lt;Form.Check
              inline
              type=&quot;radio&quot;
              label=&quot;Female&quot;
              name=&quot;<span style="color:red">gender</span>&quot; 
              value=&quot;Female&quot;
              checked={<span style="color:red">gender</span> === &quot;Female&quot;}
              onChange={(e) =&gt; <span style="color:red">setGender</span>(e.target.value)}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* DropList - Major */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Major:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Select
              value={<span style="color:red">major</span>}
              onChange={(e) =&gt; <span style="color:red">setMajor</span>(e.target.value)}
            &gt;
              &lt;option value=&quot;&quot;&gt;/option&gt;
              {<span style="color:red">majors</span>.map((m) =&gt; (
                &lt;option key={m} value={m}&gt;
                  {m}
                &lt;/option&gt;
              ))}
            &lt;/Form.Select&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* TextBox - Avatar URL (không bắt buộc) */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Avatar URL:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">avatarUrl</span>}
              onChange={(e) =&gt; <span style="color:red">setAvatarUrl</span>(e.target.value)}
              placeholder=&quot;https://example.com/image.jpg&quot;
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        &lt;Row className=&quot;mb-3&quot;&gt;
          &lt;Col sm={{ span: 10, offset: 2 }}&gt;
            &lt;Button variant=&quot;primary&quot; onClick={handleAddNew}&gt;
              Add New
            &lt;/Button&gt;
          &lt;/Col&gt;
        &lt;/Row&gt;
      &lt;/Form&gt;

      &lt;h4&gt;
        &lt;b&gt;<span style="color:red">Student List</span>&lt;/b&gt;
      &lt;/h4&gt;
      &lt;Table bordered hover&gt;
        &lt;thead&gt;
          &lt;tr&gt;
            &lt;th&gt;# No&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Avatar</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Student Name</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Gender</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Major</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Birth Date</span>&lt;/th&gt;
            &lt;th&gt;Action&lt;/th&gt;
          &lt;/tr&gt;
        &lt;/thead&gt;
        &lt;tbody&gt;
          {<span style="color:red">students</span>.map((stu, index) =&gt; (
            &lt;tr key={stu.id}&gt;
              &lt;td&gt;{String(index + 1).padStart(2, &quot;0&quot;)}&lt;/td&gt;
              {/* ✅ Image trong table */}
              &lt;td&gt;
                {stu.<span style="color:red">avatarUrl</span> ? (
                  &lt;img
                    src={stu.<span style="color:red">avatarUrl</span>}
                    alt={stu.<span style="color:red">studentName</span>}
                    width={50}
                    height={50}
                    style={{ objectFit: &quot;cover&quot;, borderRadius: &quot;50%&quot; }}
                  /&gt;
                ) : (
                  &quot;N/A&quot;
                )}
              &lt;/td&gt;
              &lt;td&gt;{stu.<span style="color:red">studentName</span>}&lt;/td&gt;
              &lt;td&gt;{stu.<span style="color:red">gender</span>}&lt;/td&gt;
              &lt;td&gt;{stu.<span style="color:red">major</span>}&lt;/td&gt;
              &lt;td&gt;{stu.<span style="color:red">birthDate</span>}&lt;/td&gt;
              &lt;td&gt;
                &lt;Button
                  variant=&quot;danger&quot;
                  size=&quot;sm&quot;
                  onClick={() =&gt; handleDeleteClick(stu)}
                &gt;
                  Delete
                &lt;/Button&gt;
                {&quot; | &quot;}
                &lt;Link to={`<span style="color:red">/student/</span>${stu.id}`}&gt;<span style="color:red">View</span>&lt;/Link&gt;
              &lt;/td&gt;
            &lt;/tr&gt;
          ))}
        &lt;/tbody&gt;
      &lt;/Table&gt;

      {/* Modal */}
      &lt;Modal show={showConfirm} onHide={handleDeleteClose} centered&gt;
        &lt;Modal.Header closeButton&gt;
          &lt;Modal.Title&gt;<span style="color:red">Confirmation</span>&lt;/Modal.Title&gt;
        &lt;/Modal.Header&gt;
        &lt;Modal.Body&gt;
          Are you sure you want to delete &quot;{deleteTarget?.<span style="color:red">studentName</span>}&quot;?
        &lt;/Modal.Body&gt;
        &lt;Modal.Footer&gt;
          &lt;Button variant=&quot;primary&quot; onClick={handleDeleteConfirm}&gt;
            <span style="color:red">Yes</span>
          &lt;/Button&gt;
          &lt;Button variant=&quot;secondary&quot; onClick={handleDeleteClose}&gt;
            <span style="color:red">Close</span>
          &lt;/Button&gt;
        &lt;/Modal.Footer&gt;
      &lt;/Modal&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">StudentList</span>;
</code></pre>

### File 4: `src/components/StudentDetail.jsx`

<pre><code>import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import <span style="color:red">StudentService</span> from "../services/<span style="color:red">StudentService</span>";

function <span style="color:red">StudentDetail</span>() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [<span style="color:red">student, setStudent</span>] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">StudentService</span>.getById(id).then((res) =&gt; <span style="color:red">setStudent</span>(res.data));
  }, [id]);

  if (!<span style="color:red">student</span>) return &lt;Container className="mt-3"&gt;<span style="color:red">Loading...</span>&lt;/Container&gt;;

  return (
    &lt;Container className="mt-3"&gt;
      &lt;h2&gt;
        &lt;b&gt;<span style="color:red">VIEW DETAILS</span>&lt;/b&gt;
      &lt;/h2&gt;
      &lt;div className="mt-4 ms-4"&gt;
        {/* ★ Image trong detail */}
        {student.<span style="color:red">avatarUrl</span> &amp;&amp; (
          &lt;div className="mb-3"&gt;
            &lt;img
              src={student.<span style="color:red">avatarUrl</span>}
              alt={student.<span style="color:red">studentName</span>}
              style={{
                maxWidth: "200px",
                maxHeight: "200px",
                borderRadius: "8px",
              }}
            /&gt;
          &lt;/div&gt;
        )}
        &lt;p&gt;&lt;b&gt;<span style="color:red">Student Name</span>:&lt;/b&gt; {student.<span style="color:red">studentName</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Gender</span>:&lt;/b&gt; {student.<span style="color:red">gender</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Major</span>:&lt;/b&gt; {student.<span style="color:red">major</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Birth Date</span>:&lt;/b&gt; {student.<span style="color:red">birthDate</span>}&lt;/p&gt;
      &lt;/div&gt;
      &lt;Button variant="outline-primary" onClick={() =&gt; navigate("/")}&gt;
        <span style="color:red">Back</span>
      &lt;/Button&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">StudentDetail</span>;</code></pre>

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

> Quy ước: phần <span style="color:red">màu đỏ</span> là token có thể thay đổi theo đề được giao (entity, field, endpoint, route, label, alert, validation rule, text button).

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

<pre><code>import axios from "axios";

const API_URL = <span style="color:red">"http://localhost:8080/api/courses"</span>;

const getAll = () =&gt; axios.get(API_URL);
const getById = (id) =&gt; axios.get(`${API_URL}/${id}`);
const create = (<span style="color:red">course</span>) =&gt; axios.post(API_URL, <span style="color:red">course</span>);
const <span style="color:red">update</span> = (id, <span style="color:red">course</span>) =&gt; axios.put(`${API_URL}/${id}`, <span style="color:red">course</span>); // ← MỚI: PUT
const <span style="color:red">deleteCourse</span> = (id) =&gt; axios.delete(`${API_URL}/${id}`);
const <span style="color:red">getLevels</span> = () =&gt; axios.get(`${API_URL}/<span style="color:red">levels</span>`);

export default { getAll, getById, create, <span style="color:red">update</span>, <span style="color:red">deleteCourse</span>, <span style="color:red">getLevels</span> };</code></pre>

### File 2: `src/App.jsx`

<pre><code>import { Routes, Route } from "react-router-dom";
import <span style="color:red">CourseList</span> from "./components/<span style="color:red">CourseList</span>";
import <span style="color:red">CourseDetail</span> from "./components/<span style="color:red">CourseDetail</span>";

function App() {
  return (
    &lt;Routes&gt;
      &lt;Route path="/" element={&lt;<span style="color:red">CourseList</span> /&gt;} /&gt;
      &lt;Route path="<span style="color:red">/course/:id</span>" element={&lt;<span style="color:red">CourseDetail</span> /&gt;} /&gt;
    &lt;/Routes&gt;
  );
}

export default App;</code></pre>

### File 3: `src/components/CourseList.jsx`

> **Điểm khác so với BSM**: Có **Edit (PUT/Update)** + **CheckBox** + nút **Edit** trên table

<pre><code>import { useState, useEffect } from &quot;react&quot;;
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from &quot;react-bootstrap&quot;;
import { Link } from &quot;react-router-dom&quot;;
import <span style="color:red">CourseService</span> from &quot;../services/<span style="color:red">CourseService</span>&quot;;

function <span style="color:red">CourseList</span>() {
  const [<span style="color:red">courses</span>, <span style="color:red">setCourses</span>] = useState([]);
  const [<span style="color:red">levels</span>, <span style="color:red">setLevels</span>] = useState([]);
  const [<span style="color:red">courseName</span>, <span style="color:red">setCourseName</span>] = useState(&quot;&quot;);
  const [<span style="color:red">credits</span>, <span style="color:red">setCredits</span>] = useState(&quot;&quot;);
  const [<span style="color:red">instructor</span>, <span style="color:red">setInstructor</span>] = useState(&quot;&quot;);
  const [<span style="color:red">level</span>, <span style="color:red">setLevel</span>] = useState(&quot;&quot;);
  const [<span style="color:red">active</span>, <span style="color:red">setActive</span>] = useState(true); 

  
  const [<span style="color:red">editId</span>, <span style="color:red">setEditId</span>] = useState(null); // null = Add mode, có id = Edit mode

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">loadCourses</span>();
    <span style="color:red">loadLevels</span>();
  }, []);

  const <span style="color:red">loadCourses</span> = () =&gt; {
    <span style="color:red">CourseService</span>.getAll().then((res) =&gt; <span style="color:red">setCourses</span>(res.data));
  };

  const <span style="color:red">loadLevels</span> = () =&gt; {
    <span style="color:red">CourseService</span>.<span style="color:red">getLevels</span>().then((res) =&gt; <span style="color:red">setLevels</span>(res.data));
  };

  
  const <span style="color:red">resetForm</span> = () =&gt; {
    <span style="color:red">setCourseName</span>(&quot;&quot;);
    <span style="color:red">setCredits</span>(&quot;&quot;);
    <span style="color:red">setInstructor</span>(&quot;&quot;);
    <span style="color:red">setLevel</span>(&quot;&quot;);
    <span style="color:red">setActive</span>(true);
    <span style="color:red">setEditId</span>(null); 
  };

  
  const <span style="color:red">handleSubmit</span> = () =&gt; {
    // Validation (giống nhau cho cả Add và Update)
    if (!<span style="color:red">courseName</span>.trim()) {
      alert(&quot;<span style="color:red">Course Name is required</span>&quot;);
      return;
    }
    if (<span style="color:red">courseName</span>.trim().length &gt; 100) {
      alert(&quot;<span style="color:red">Course Name must be at most 100 characters</span>&quot;);
      return;
    }

    if (!<span style="color:red">credits</span>) {
      alert(&quot;<span style="color:red">Credits is required</span>&quot;);
      return;
    }
    const <span style="color:red">creditsNum</span> = Number(<span style="color:red">credits</span>);
    if (isNaN(<span style="color:red">creditsNum</span>) || <span style="color:red">creditsNum</span> &lt; 1 || <span style="color:red">creditsNum</span> &gt; 10) {
      alert(&quot;<span style="color:red">Credits must be between 1 and 10</span>&quot;);
      return;
    }

    if (!<span style="color:red">instructor</span>.trim()) {
      alert(&quot;<span style="color:red">Instructor is required</span>&quot;);
      return;
    }
    if (<span style="color:red">instructor</span>.trim().length &gt; 80) {
      alert(&quot;<span style="color:red">Instructor must be at most 80 characters</span>&quot;);
      return;
    }

    if (!<span style="color:red">level</span>) {
      alert(&quot;<span style="color:red">Level is required</span>&quot;);
      return;
    }

    const <span style="color:red">course</span> = {
      <span style="color:red">courseName</span>: <span style="color:red">courseName</span>.trim(),
      <span style="color:red">credits</span>: <span style="color:red">creditsNum</span>,
      <span style="color:red">instructor</span>: <span style="color:red">instructor</span>.trim(),
      <span style="color:red">level</span>,
      <span style="color:red">active</span>,
    };

    if (<span style="color:red">editId</span>) {
      
      <span style="color:red">CourseService</span>.<span style="color:red">update</span>(<span style="color:red">editId</span>, course)
        .then(() =&gt; {
          alert(&quot;<span style="color:red">Updated Course successfully</span>&quot;);
          <span style="color:red">loadCourses</span>();
          resetForm(); 
        })
        .catch((err) =&gt; {
          alert(err.response?.data?.message || &quot;<span style="color:red">Error updating course</span>&quot;);
        });
    } else {
      // MODE: CREATE (POST)
      <span style="color:red">CourseService</span>.create(<span style="color:red">course</span>)
        .then(() =&gt; {
          alert(&quot;<span style="color:red">Created new Course successfully</span>&quot;);
          <span style="color:red">loadCourses</span>();
          resetForm();
        })
        .catch((err) =&gt; {
          alert(err.response?.data?.message || &quot;<span style="color:red">Error creating course</span>&quot;);
        });
    }
  };

  
  const <span style="color:red">handleEditClick</span> = (course) =&gt; {
    <span style="color:red">setEditId</span>(course.id); 
    <span style="color:red">setCourseName</span>(course.<span style="color:red">courseName</span>);
    <span style="color:red">setCredits</span>(String(course.<span style="color:red">credits</span>)); 
    <span style="color:red">setInstructor</span>(course.<span style="color:red">instructor</span>);
    <span style="color:red">setLevel</span>(course.<span style="color:red">level</span>);
    <span style="color:red">setActive</span>(course.<span style="color:red">active</span>);
  };

  const handleDeleteClick = (course) =&gt; {
    setDeleteTarget(course);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () =&gt; {
    <span style="color:red">CourseService</span>.<span style="color:red">deleteCourse</span>(deleteTarget.id).then(() =&gt; {
      alert(&quot;
      <span style="color:red">loadCourses</span>();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () =&gt; {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    &lt;Container&gt;
      &lt;h2 className=&quot;mt-3 mb-3&quot;&gt;
        &lt;b&gt;<span style="color:red">Course Management</span>&lt;/b&gt;
      &lt;/h2&gt;
      &lt;Form&gt;
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Course Name:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">courseName</span>}
              onChange={(e) =&gt; <span style="color:red">setCourseName</span>(e.target.value)}
              maxLength={100}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Credits:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">credits</span>}
              onChange={(e) =&gt; <span style="color:red">setCredits</span>(e.target.value)}
              maxLength={2}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Instructor:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Control
              type=&quot;text&quot;
              value={<span style="color:red">instructor</span>}
              onChange={(e) =&gt; <span style="color:red">setInstructor</span>(e.target.value)}
              maxLength={80}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Form.Label column sm={2} className=&quot;text-end&quot;&gt;
            Level:
          &lt;/Form.Label&gt;
          &lt;Col sm={10}&gt;
            &lt;Form.Select
              value={<span style="color:red">level</span>}
              onChange={(e) =&gt; <span style="color:red">setLevel</span>(e.target.value)}
            &gt;
              &lt;option value=&quot;&quot;&gt;/option&gt;
              {<span style="color:red">levels</span>.map((l) =&gt; (
                &lt;option key={l} value={l}&gt;
                  {l}
                &lt;/option&gt;
              ))}
            &lt;/Form.Select&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* CheckBox */}
        &lt;Form.Group as={Row} className=&quot;mb-2&quot;&gt;
          &lt;Col sm={{ span: 10, offset: 2 }}&gt;
            &lt;Form.Check
              type=&quot;checkbox&quot;
              label=&quot;<span style="color:red">Active</span>&quot;
              checked={<span style="color:red">active</span>}
              onChange={(e) =&gt; <span style="color:red">setActive</span>(e.target.checked)}
            /&gt;
          &lt;/Col&gt;
        &lt;/Form.Group&gt;

        {/* ✅ Nút thay đổi text theo mode */}
        &lt;Row className=&quot;mb-3&quot;&gt;
          &lt;Col sm={{ span: 10, offset: 2 }}&gt;
            &lt;Button variant=&quot;primary&quot; onClick={<span style="color:red">handleSubmit</span>}&gt;
              {<span style="color:red">editId</span> ? &quot;Update&quot; : &quot;Add New&quot;} {/* ✅ Đổi text */}
            &lt;/Button&gt;
            {/* ✅ Nút Cancel chỉ hiện khi đang Edit */}
            {<span style="color:red">editId</span> &amp;&amp; (
              &lt;Button variant=&quot;secondary&quot; className=&quot;ms-2&quot; onClick={<span style="color:red">resetForm</span>}&gt;
                Cancel
              &lt;/Button&gt;
            )}
          &lt;/Col&gt;
        &lt;/Row&gt;
      &lt;/Form&gt;

      &lt;h4&gt;
        &lt;b&gt;<span style="color:red">Course List</span>&lt;/b&gt;
      &lt;/h4&gt;
      &lt;Table bordered hover&gt;
        &lt;thead&gt;
          &lt;tr&gt;
            &lt;th&gt;# No&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Course Name</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Level</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Instructor</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Credits</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Active</span>&lt;/th&gt;
            &lt;th&gt;Action&lt;/th&gt;
          &lt;/tr&gt;
        &lt;/thead&gt;
        &lt;tbody&gt;
          {<span style="color:red">courses</span>.map((course, index) =&gt; (
            &lt;tr key={course.id}&gt;
              &lt;td&gt;{String(index + 1).padStart(2, &quot;0&quot;)}&lt;/td&gt;
              &lt;td&gt;{course.<span style="color:red">courseName</span>}&lt;/td&gt;
              &lt;td&gt;{course.<span style="color:red">level</span>}&lt;/td&gt;
              &lt;td&gt;{course.<span style="color:red">instructor</span>}&lt;/td&gt;
              &lt;td&gt;{course.<span style="color:red">credits</span>}&lt;/td&gt;
              &lt;td&gt;{course.<span style="color:red">active</span> ? &quot;<span style="color:red">Yes</span>&quot; : &quot;<span style="color:red">No</span>&quot;}&lt;/td&gt;
              &lt;td&gt;
                {/* ✅ MỚI: Nút Edit */}
                &lt;Button
                  variant=&quot;warning&quot;
                  size=&quot;sm&quot;
                  onClick={() =&gt; handleEditClick(course)}
                &gt;
                  Edit
                &lt;/Button&gt;
                {&quot; | &quot;}
                &lt;Button
                  variant=&quot;danger&quot;
                  size=&quot;sm&quot;
                  onClick={() =&gt; handleDeleteClick(course)}
                &gt;
                  Delete
                &lt;/Button&gt;
                {&quot; | &quot;}
                &lt;Link to={`<span style="color:red">/course/</span>${course.id}`}&gt;<span style="color:red">View</span>&lt;/Link&gt;
              &lt;/td&gt;
            &lt;/tr&gt;
          ))}
        &lt;/tbody&gt;
      &lt;/Table&gt;

      {/* Modal xác nhận xóa */}
      &lt;Modal show={showConfirm} onHide={handleDeleteClose} centered&gt;
        &lt;Modal.Header closeButton&gt;
          &lt;Modal.Title&gt;<span style="color:red">Confirmation</span>&lt;/Modal.Title&gt;
        &lt;/Modal.Header&gt;
        &lt;Modal.Body&gt;
          Are you sure you want to delete &quot;{deleteTarget?.<span style="color:red">courseName</span>}&quot;?
        &lt;/Modal.Body&gt;
        &lt;Modal.Footer&gt;
          &lt;Button variant=&quot;primary&quot; onClick={handleDeleteConfirm}&gt;
            <span style="color:red">Yes</span>
          &lt;/Button&gt;
          &lt;Button variant=&quot;secondary&quot; onClick={handleDeleteClose}&gt;
            <span style="color:red">Close</span>
          &lt;/Button&gt;
        &lt;/Modal.Footer&gt;
      &lt;/Modal&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">CourseList</span>;
</code></pre>

### File 4: `src/components/CourseDetail.jsx`

<pre><code>import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import <span style="color:red">CourseService</span> from "../services/<span style="color:red">CourseService</span>";

function <span style="color:red">CourseDetail</span>() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [<span style="color:red">course, setCourse</span>] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">CourseService</span>.getById(id).then((res) =&gt; <span style="color:red">setCourse</span>(res.data));
  }, [id]);

  if (!<span style="color:red">course</span>) return &lt;Container className="mt-3"&gt;<span style="color:red">Loading...</span>&lt;/Container&gt;;

  return (
    &lt;Container className="mt-3"&gt;
      &lt;h2&gt;
        &lt;b&gt;<span style="color:red">VIEW DETAILS</span>&lt;/b&gt;
      &lt;/h2&gt;
      &lt;div className="mt-4 ms-4"&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Course Name</span>:&lt;/b&gt; {course.<span style="color:red">courseName</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Instructor</span>:&lt;/b&gt; {course.<span style="color:red">instructor</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Level</span>:&lt;/b&gt; {course.<span style="color:red">level</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Credits</span>:&lt;/b&gt; {course.<span style="color:red">credits</span>}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Active</span>:&lt;/b&gt; {course.<span style="color:red">active</span> ? "<span style="color:red">Yes</span>" : "<span style="color:red">No</span>"}&lt;/p&gt;
      &lt;/div&gt;
      &lt;Button variant="outline-primary" onClick={() =&gt; navigate("/")}&gt;
        <span style="color:red">Back</span>
      &lt;/Button&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">CourseDetail</span>;</code></pre>

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

# ĐỀ 5: EVENT MANAGEMENT (EVM)

## 5.1. Yêu cầu đề bài

### Entity: Event

```java
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String eventDate;   // yyyy-MM-dd

    @Column(nullable = false)
    private int seats;

    @Column(nullable = false)
    private boolean online;

    @Column(nullable = false)
    private String level;       // Beginner / Intermediate / Advanced

    @Column(nullable = false, length = 255)
    private String bannerUrl;
}
```

### API Endpoints:

| Method | URL                                                 | Mô tả                               |
| ------ | --------------------------------------------------- | ----------------------------------- |
| GET    | `/api/events`                                       | Lấy tất cả                          |
| GET    | `/api/events/{id}`                                  | Lấy theo id                         |
| POST   | `/api/events`                                       | Tạo mới                             |
| PUT    | `/api/events/{id}`                                  | Cập nhật                            |
| DELETE | `/api/events/{id}`                                  | Xóa                                 |
| GET    | `/api/events/categories`                            | Lấy danh sách category (DropList)   |
| GET    | `/api/events/search?keyword=...`                    | Tìm theo title/description/category |
| GET    | `/api/events/search?keyword=...&sort=eventDate,asc` | Tìm + sắp xếp                       |

### Validation:

- Title: bắt buộc, tối đa 120 ký tự, không trùng
- Description: bắt buộc, tối đa 1000 ký tự (TextArea)
- Category: bắt buộc, chọn từ dropdown
- Event Date: bắt buộc, không được nhỏ hơn ngày hiện tại
- Seats: bắt buộc, số nguyên > 0 và <= 5000
- Online: checkbox (boolean)
- Level: bắt buộc, chọn 1 trong 3 radio options
- Banner URL: bắt buộc, đúng định dạng URL

### Màn hình và component cần có:

**Screen 1 - List + Form:**

- Form controls đầy đủ: TextBox, Number, Date, TextArea, DropList, CheckBox, Radio, Image preview
- Button: Add New / Update / Cancel
- Search bar: keyword + sort + Search + Clear
- Table: # No, Banner, Title, Category, Date, Seats, Online, Level, Action
- Pagination: Prev / Next + page info

**Screen 2 - Confirmation:**

- Modal xác nhận xóa

**Screen 3 - Detail Screen:**

- Hiển thị toàn bộ field
- Button: Back

---

## 5.2. Hướng dẫn giải từng bước theo thứ tự

### Bước 1: Tạo skeleton file và routing

1. Tạo `src/services/EventService.js`, `src/components/EventList.jsx`, `src/components/EventDetail.jsx`.
2. Cập nhật `src/App.jsx`:
   - Route list: `/`
   - Route detail: `/event/:id`

### Bước 2: Viết EventService đầy đủ CRUD + Search

Trong `EventService.js`, tạo các hàm theo đúng thứ tự để dễ test:

1. `getAll()`
2. `getById(id)`
3. `create(event)`
4. `update(id, event)`
5. `deleteEvent(id)`
6. `getCategories()`
7. `search(keyword, sort, page, size)`

Gợi ý service:

```javascript
import axios from "axios";

const API_URL = "http://localhost:8080/api/events";

const getAll = () => axios.get(API_URL);
const getById = (id) => axios.get(`${API_URL}/${id}`);
const create = (event) => axios.post(API_URL, event);
const update = (id, event) => axios.put(`${API_URL}/${id}`, event);
const deleteEvent = (id) => axios.delete(`${API_URL}/${id}`);
const getCategories = () => axios.get(`${API_URL}/categories`);
const search = (keyword, sort, page = 0, size = 5) =>
  axios.get(`${API_URL}/search`, { params: { keyword, sort, page, size } });

export default {
  getAll,
  getById,
  create,
  update,
  deleteEvent,
  getCategories,
  search,
};
```

### Bước 3: Khai báo state theo nhóm trong EventList

1. Data state: `events`, `categories`, `totalPages`, `page`.
2. Form state: `title`, `description`, `category`, `eventDate`, `seats`, `online`, `level`, `bannerUrl`.
3. Search state: `keyword`, `sort`.
4. Control state: `editId`, `showConfirm`, `deleteTarget`.

### Bước 4: Tạo useEffect để load dữ liệu ban đầu

1. `loadEvents()` để lấy danh sách trang đầu.
2. `loadCategories()` để đổ dropdown.
3. Gọi cả 2 trong `useEffect(..., [])`.

### Bước 5: Dựng form đầy đủ tất cả control

1. TextBox: `title`, `bannerUrl`
2. TextArea: `description`
3. DropList: `category`
4. Date: `eventDate`
5. Number: `seats`
6. CheckBox: `online`
7. Radio group: `level` (Beginner/Intermediate/Advanced)
8. Ảnh preview: render `<img src={bannerUrl} ... />` khi URL hợp lệ

### Bước 6: Viết validation theo đúng thứ tự ngắn gọn

Thứ tự đề xuất để debug nhanh:

1. Required checks
2. Length checks
3. Number range checks
4. Date checks
5. URL format checks

Nếu fail: `alert("...")` và `return` ngay.

### Bước 7: Viết handleSubmit cho cả Add và Update

1. Validate
2. Tạo object `eventPayload`
3. Nếu `editId` có giá trị: gọi `update(editId, eventPayload)`
4. Nếu không: gọi `create(eventPayload)`
5. Sau thành công: alert + reload list + reset form

### Bước 8: Viết resetForm

Reset tất cả field + `setEditId(null)` để thoát mode edit.

### Bước 9: Viết chức năng Edit

`handleEditClick(event)`:

1. set `editId`
2. đổ dữ liệu event vào toàn bộ state form
3. với `seats` có thể dùng `String(event.seats)` để bind vào input number

### Bước 10: Viết chức năng Delete qua modal xác nhận

1. `handleDeleteClick(event)` mở modal
2. `handleDeleteConfirm()` gọi `deleteEvent(deleteTarget.id)`
3. Thành công: alert + reload + đóng modal + clear target

### Bước 11: Viết Search + Sort

1. `handleSearch()` gọi `search(keyword, sort, 0, size)`
2. Reset page về 0 khi search mới
3. `handleClearSearch()` xoá keyword + sort, load lại list gốc

### Bước 12: Viết Pagination

1. Nút Prev: disable khi `page === 0`
2. Nút Next: disable khi `page + 1 >= totalPages`
3. Khi đổi page: gọi lại API search hoặc getAll tương ứng trạng thái hiện tại

### Bước 13: Dựng bảng danh sách và action

Trong mỗi row:

1. Edit button
2. Delete button
3. View link `/event/${event.id}`

### Bước 14: Viết EventDetail

1. Lấy `id` từ `useParams`
2. Gọi `EventService.getById(id)`
3. Hiển thị toàn bộ field (kèm ảnh banner)
4. Nút Back quay về list

### Bước 15: Checklist chạy thử cuối cùng

1. Add thành công 1 bản ghi
2. Edit thành công 1 bản ghi
3. Delete thành công 1 bản ghi
4. Search theo title chạy đúng
5. Sort theo date chạy đúng
6. Pagination qua lại đúng
7. Detail hiển thị đúng toàn bộ field
8. Không có warning/error trong console

---

## 5.3. Lời giải chi tiết Đề 5 (đánh dấu đỏ phần cần thay đổi)

> Quy ước mới: phần <span style="color:red">màu đỏ</span> là token có thể thay đổi theo đề được giao (entity, field, endpoint, route, label, alert, rule, sort key, page size...).

### File 1: `src/services/EventService.js`

<pre><code>import axios from "axios";

const API_URL = <span style="color:red">"http://localhost:8080/api/events"</span>;

const getAll = () =&gt; axios.get(API_URL);
const getById = (id) =&gt; axios.get(`${API_URL}/${id}`);
const create = (<span style="color:red">event</span>) =&gt; axios.post(API_URL, <span style="color:red">event</span>);
const <span style="color:red">update</span> = (id, <span style="color:red">event</span>) =&gt; axios.put(`${API_URL}/${id}`, <span style="color:red">event</span>);
const <span style="color:red">deleteEvent</span> = (id) =&gt; axios.delete(`${API_URL}/${id}`);
const <span style="color:red">getCategories</span> = () =&gt; axios.get(`${API_URL}/<span style="color:red">categories</span>`);
const <span style="color:red">search</span> = (keyword, sort, page = 0, size = <span style="color:red">5</span>) =&gt;
  axios.get(`${API_URL}/<span style="color:red">search</span>`, {
    params: { <span style="color:red">keyword</span>, <span style="color:red">sort</span>, <span style="color:red">page</span>, <span style="color:red">size</span> },
  });

export default {
  getAll,
  getById,
  create,
  <span style="color:red">update</span>,
  <span style="color:red">deleteEvent</span>,
  <span style="color:red">getCategories</span>,
  <span style="color:red">search</span>,
};</code></pre>

### File 2: `src/App.jsx`

<pre><code>import { Routes, Route } from "react-router-dom";
import <span style="color:red">EventList</span> from "./components/<span style="color:red">EventList</span>";
import <span style="color:red">EventDetail</span> from "./components/<span style="color:red">EventDetail</span>";

function App() {
  return (
    &lt;Routes&gt;
      &lt;Route path="/" element={&lt;<span style="color:red">EventList</span> /&gt;} /&gt;
      &lt;Route path="<span style="color:red">/event/:id</span>" element={&lt;<span style="color:red">EventDetail</span> /&gt;} /&gt;
    &lt;/Routes&gt;
  );
}

export default App;</code></pre>

### File 3: `src/components/EventList.jsx`

<pre><code>import { useEffect, useState } from "react";
import { Button, Col, Container, Form, Modal, Row, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import <span style="color:red">EventService</span> from "../services/<span style="color:red">EventService</span>";

function <span style="color:red">EventList</span>() {
  const [<span style="color:red">events</span>, <span style="color:red">setEvents</span>] = useState([]);
  const [<span style="color:red">categories</span>, <span style="color:red">setCategories</span>] = useState([]);

  const [<span style="color:red">title</span>, <span style="color:red">setTitle</span>] = useState("");
  const [<span style="color:red">description</span>, <span style="color:red">setDescription</span>] = useState("");
  const [<span style="color:red">category</span>, <span style="color:red">setCategory</span>] = useState("");
  const [<span style="color:red">eventDate</span>, <span style="color:red">setEventDate</span>] = useState("");
  const [<span style="color:red">seats</span>, <span style="color:red">setSeats</span>] = useState("");
  const [<span style="color:red">online</span>, <span style="color:red">setOnline</span>] = useState(false);
  const [<span style="color:red">level</span>, <span style="color:red">setLevel</span>] = useState("");
  const [<span style="color:red">bannerUrl</span>, <span style="color:red">setBannerUrl</span>] = useState("");

  const [<span style="color:red">keyword</span>, <span style="color:red">setKeyword</span>] = useState("");
  const [<span style="color:red">sort</span>, <span style="color:red">setSort</span>] = useState("<span style="color:red">eventDate,asc</span>");
  const [<span style="color:red">page</span>, <span style="color:red">setPage</span>] = useState(0);
  const [<span style="color:red">size</span>] = useState(<span style="color:red">5</span>);
  const [<span style="color:red">totalPages</span>, <span style="color:red">setTotalPages</span>] = useState(1);

  const [<span style="color:red">editId</span>, <span style="color:red">setEditId</span>] = useState(null);
  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">loadCategories</span>();
    <span style="color:red">loadEvents</span>(0, keyword, sort);
  }, []);

  const <span style="color:red">loadCategories</span> = () =&gt; {
    <span style="color:red">EventService.getCategories</span>().then((res) =&gt; <span style="color:red">setCategories</span>(res.data));
  };

  const <span style="color:red">loadEvents</span> = (nextPage = 0, nextKeyword = "", nextSort = "<span style="color:red">eventDate,asc</span>") =&gt; {
    <span style="color:red">EventService.search</span>(nextKeyword, nextSort, nextPage, size).then((res) =&gt; {
      <span style="color:red">setEvents</span>(res.data.content ?? []);
      <span style="color:red">setPage</span>(res.data.number ?? 0);
      <span style="color:red">setTotalPages</span>(res.data.totalPages || 1);
    });
  };

  const <span style="color:red">resetForm</span> = () =&gt; {
    setTitle("");
    setDescription("");
    setCategory("");
    setEventDate("");
    setSeats("");
    setOnline(false);
    setLevel("");
    setBannerUrl("");
    <span style="color:red">setEditId</span>(null);
  };

  const validate = () =&gt; {
    if (!title.trim()) return alert("<span style="color:red">Title is required</span>"), false;
    if (title.length &gt; <span style="color:red">120</span>) return alert("<span style="color:red">Title max length is 120</span>"), false;
    if (!description.trim()) return alert("<span style="color:red">Description is required</span>"), false;
    if (description.length &gt; <span style="color:red">1000</span>) return alert("<span style="color:red">Description max length is 1000</span>"), false;
    if (!category) return alert("<span style="color:red">Category is required</span>"), false;
    if (!eventDate) return alert("<span style="color:red">Event date is required</span>"), false;
    if (new Date(eventDate) &lt; new Date(new Date().toDateString())) return alert("<span style="color:red">Event date must be today or in the future</span>"), false;
    const seatsNum = Number(seats);
    if (Number.isNaN(seatsNum) || seatsNum &lt;= <span style="color:red">0</span> || seatsNum &gt; <span style="color:red">5000</span>) return alert("<span style="color:red">Seats must be &gt; 0 and &lt;= 5000</span>"), false;
    if (!level) return alert("<span style="color:red">Level is required</span>"), false;
    if (!bannerUrl.trim()) return alert("<span style="color:red">Banner URL is required</span>"), false;
    if (!bannerUrl.startsWith("<span style="color:red">http://</span>") &amp;&amp; !bannerUrl.startsWith("<span style="color:red">https://</span>")) return alert("<span style="color:red">Banner URL must start with http:// or https://</span>"), false;
    return true;
  };

  const <span style="color:red">handleSubmit</span> = () =&gt; {
    if (!validate()) return;

    const eventPayload = {
      title: title.trim(),
      description: description.trim(),
      category,
      eventDate,
      seats: Number(seats),
      online,
      level,
      bannerUrl: bannerUrl.trim(),
    };

    const request = <span style="color:red">editId</span>
      ? <span style="color:red">EventService.update(editId, eventPayload)</span>
      : <span style="color:red">EventService.create(eventPayload)</span>;

    request.then(() =&gt; {
      alert(<span style="color:red">editId ? "Updated successfully" : "Created successfully"</span>);
      <span style="color:red">loadEvents</span>(page, keyword, sort);
      <span style="color:red">resetForm</span>();
    });
  };

  const <span style="color:red">handleEditClick</span> = (event) =&gt; {
    <span style="color:red">setEditId</span>(event.id);
    setTitle(event.title);
    setDescription(event.description);
    setCategory(event.category);
    setEventDate(event.eventDate);
    setSeats(String(event.seats));
    setOnline(event.online);
    setLevel(event.level);
    setBannerUrl(event.bannerUrl);
  };

  const handleDeleteClick = (event) =&gt; {
    setDeleteTarget(event);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () =&gt; {
    <span style="color:red">EventService.deleteEvent</span>(deleteTarget.id).then(() =&gt; {
      alert("<span style="color:red">Deleted successfully</span>");
      <span style="color:red">loadEvents</span>(page, keyword, sort);
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const <span style="color:red">handleSearch</span> = () =&gt; {
    <span style="color:red">loadEvents</span>(0, keyword, sort);
  };

  const <span style="color:red">handleClearSearch</span> = () =&gt; {
    setKeyword("");
    setSort("<span style="color:red">eventDate,asc</span>");
    <span style="color:red">loadEvents</span>(0, "", "<span style="color:red">eventDate,asc</span>");
  };

  const <span style="color:red">handlePrev</span> = () =&gt; {
    if (page &gt; 0) <span style="color:red">loadEvents</span>(page - 1, keyword, sort);
  };

  const <span style="color:red">handleNext</span> = () =&gt; {
    if (page + 1 &lt; totalPages) <span style="color:red">loadEvents</span>(page + 1, keyword, sort);
  };

  return (
    &lt;Container&gt;
      &lt;h2 className="mt-3 mb-3"&gt;&lt;b&gt;<span style="color:red">Event Management</span>&lt;/b&gt;&lt;/h2&gt;

      &lt;Form className="mb-4"&gt;
        {/* Search + sort */}
        &lt;Row className="mb-3"&gt;
          &lt;Col md={5}&gt;
            &lt;Form.Control value={keyword} onChange={(e) =&gt; setKeyword(e.target.value)} placeholder="<span style="color:red">Search by title/description/category</span>" /&gt;
          &lt;/Col&gt;
          &lt;Col md={3}&gt;
            &lt;Form.Select value={sort} onChange={(e) =&gt; setSort(e.target.value)}&gt;
              &lt;option value="<span style="color:red">eventDate,asc</span>"&gt;<span style="color:red">Date ASC</span>&lt;/option&gt;
              &lt;option value="<span style="color:red">eventDate,desc</span>"&gt;<span style="color:red">Date DESC</span>&lt;/option&gt;
              &lt;option value="<span style="color:red">title,asc</span>"&gt;<span style="color:red">Title A-Z</span>&lt;/option&gt;
              &lt;option value="<span style="color:red">title,desc</span>"&gt;<span style="color:red">Title Z-A</span>&lt;/option&gt;
            &lt;/Form.Select&gt;
          &lt;/Col&gt;
          &lt;Col md={4}&gt;
            &lt;Button variant="primary" onClick={handleSearch}&gt;<span style="color:red">Search</span>&lt;/Button&gt;{" "}
            &lt;Button variant="secondary" onClick={handleClearSearch}&gt;<span style="color:red">Clear</span>&lt;/Button&gt;
          &lt;/Col&gt;
        &lt;/Row&gt;

        {/* Form fields */}
        &lt;Form.Control className="mb-2" value={title} onChange={(e) =&gt; setTitle(e.target.value)} placeholder="<span style="color:red">Title</span>" /&gt;
        &lt;Form.Control className="mb-2" as="textarea" rows={<span style="color:red">3</span>} value={description} onChange={(e) =&gt; setDescription(e.target.value)} placeholder="<span style="color:red">Description</span>" /&gt;
        &lt;Row className="mb-2"&gt;
          &lt;Col&gt;
            &lt;Form.Select value={category} onChange={(e) =&gt; setCategory(e.target.value)}&gt;
              &lt;option value=""&gt;<span style="color:red">-- Select Category --</span>&lt;/option&gt;
              {categories.map((c) =&gt; (&lt;option key={c} value={c}&gt;{c}&lt;/option&gt;))}
            &lt;/Form.Select&gt;
          &lt;/Col&gt;
          &lt;Col&gt;&lt;Form.Control type="date" value={eventDate} onChange={(e) =&gt; setEventDate(e.target.value)} /&gt;&lt;/Col&gt;
          &lt;Col&gt;&lt;Form.Control type="number" value={seats} onChange={(e) =&gt; setSeats(e.target.value)} placeholder="<span style="color:red">Seats</span>" /&gt;&lt;/Col&gt;
        &lt;/Row&gt;

        &lt;Row className="mb-2"&gt;
          &lt;Col md={4}&gt;
            &lt;Form.Check type="checkbox" label="<span style="color:red">Online Event</span>" checked={online} onChange={(e) =&gt; setOnline(e.target.checked)} /&gt;
          &lt;/Col&gt;
          &lt;Col md={8}&gt;
            &lt;Form.Check inline type="radio" label="<span style="color:red">Beginner</span>" name="<span style="color:red">level</span>" value="<span style="color:red">Beginner</span>" checked={level === "<span style="color:red">Beginner</span>"} onChange={(e) =&gt; setLevel(e.target.value)} /&gt;
            &lt;Form.Check inline type="radio" label="<span style="color:red">Intermediate</span>" name="<span style="color:red">level</span>" value="<span style="color:red">Intermediate</span>" checked={level === "<span style="color:red">Intermediate</span>"} onChange={(e) =&gt; setLevel(e.target.value)} /&gt;
            &lt;Form.Check inline type="radio" label="<span style="color:red">Advanced</span>" name="<span style="color:red">level</span>" value="<span style="color:red">Advanced</span>" checked={level === "<span style="color:red">Advanced</span>"} onChange={(e) =&gt; setLevel(e.target.value)} /&gt;
          &lt;/Col&gt;
        &lt;/Row&gt;

        &lt;Form.Control className="mb-2" value={bannerUrl} onChange={(e) =&gt; setBannerUrl(e.target.value)} placeholder="<span style="color:red">Banner URL</span>" /&gt;
        {bannerUrl &amp;&amp; (&lt;img src={bannerUrl} alt="<span style="color:red">preview</span>" style={{ width: <span style="color:red">180</span>, height: <span style="color:red">100</span>, objectFit: "<span style="color:red">cover</span>" }} /&gt;)}

        &lt;div className="mt-3"&gt;
          &lt;Button variant="primary" onClick={handleSubmit}&gt;{<span style="color:red">editId ? "Update" : "Add New"</span>}&lt;/Button&gt;{" "}
          {<span style="color:red">editId</span> &amp;&amp; (&lt;Button variant="secondary" onClick={resetForm}&gt;<span style="color:red">Cancel</span>&lt;/Button&gt;)}
        &lt;/div&gt;
      &lt;/Form&gt;

      &lt;h4&gt;&lt;b&gt;<span style="color:red">Event List</span>&lt;/b&gt;&lt;/h4&gt;
      &lt;Table bordered hover&gt;
        &lt;thead&gt;
          &lt;tr&gt;
            &lt;th&gt;# No&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Banner</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Title</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Category</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Date</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Seats</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Online</span>&lt;/th&gt;
            &lt;th&gt;<span style="color:red">Level</span>&lt;/th&gt;
            &lt;th&gt;Action&lt;/th&gt;
          &lt;/tr&gt;
        &lt;/thead&gt;
        &lt;tbody&gt;
          {events.map((event, index) =&gt; (
            &lt;tr key={event.id}&gt;
              &lt;td&gt;{String(page * size + index + 1).padStart(2, "0")}&lt;/td&gt;
              &lt;td&gt;&lt;img src={event.bannerUrl} alt={event.title} style={{ width: <span style="color:red">90</span>, height: <span style="color:red">50</span>, objectFit: "<span style="color:red">cover</span>" }} /&gt;&lt;/td&gt;
              &lt;td&gt;{event.title}&lt;/td&gt;
              &lt;td&gt;{event.category}&lt;/td&gt;
              &lt;td&gt;{event.eventDate}&lt;/td&gt;
              &lt;td&gt;{event.seats}&lt;/td&gt;
              &lt;td&gt;{event.online ? "<span style="color:red">Yes</span>" : "<span style="color:red">No</span>"}&lt;/td&gt;
              &lt;td&gt;{event.level}&lt;/td&gt;
              &lt;td&gt;
                &lt;Button variant="warning" size="sm" onClick={() =&gt; handleEditClick(event)}&gt;<span style="color:red">Edit</span>&lt;/Button&gt;{" | "}
                &lt;Button variant="danger" size="sm" onClick={() =&gt; handleDeleteClick(event)}&gt;<span style="color:red">Delete</span>&lt;/Button&gt;{" | "}
                &lt;Link to={`<span style="color:red">/event</span>/${event.id}`}&gt;<span style="color:red">View</span>&lt;/Link&gt;
              &lt;/td&gt;
            &lt;/tr&gt;
          ))}
        &lt;/tbody&gt;
      &lt;/Table&gt;

      &lt;div className="d-flex gap-2 mb-3"&gt;
        &lt;Button onClick={handlePrev} disabled={page === 0}&gt;<span style="color:red">Prev</span>&lt;/Button&gt;
        &lt;Button onClick={handleNext} disabled={page + 1 &gt;= totalPages}&gt;<span style="color:red">Next</span>&lt;/Button&gt;
        &lt;span className="align-self-center"&gt;<span style="color:red">Page</span> {page + 1} / {totalPages}&lt;/span&gt;
      &lt;/div&gt;

      &lt;Modal show={showConfirm} onHide={() =&gt; setShowConfirm(false)} centered&gt;
        &lt;Modal.Header closeButton&gt;
          &lt;Modal.Title&gt;<span style="color:red">Confirmation</span>&lt;/Modal.Title&gt;
        &lt;/Modal.Header&gt;
        &lt;Modal.Body&gt;<span style="color:red">Are you sure you want to delete</span> "{deleteTarget?.title}"?&lt;/Modal.Body&gt;
        &lt;Modal.Footer&gt;
          &lt;Button variant="primary" onClick={handleDeleteConfirm}&gt;<span style="color:red">Yes</span>&lt;/Button&gt;
          &lt;Button variant="secondary" onClick={() =&gt; setShowConfirm(false)}&gt;<span style="color:red">Close</span>&lt;/Button&gt;
        &lt;/Modal.Footer&gt;
      &lt;/Modal&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">EventList</span>;</code></pre>

### File 4: `src/components/EventDetail.jsx`

<pre><code>import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Container } from "react-bootstrap";
import <span style="color:red">EventService</span> from "../services/<span style="color:red">EventService</span>";

function <span style="color:red">EventDetail</span>() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [<span style="color:red">event</span>, <span style="color:red">setEvent</span>] = useState(null);

  useEffect(() =&gt; {
    <span style="color:red">EventService</span>.getById(id).then((res) =&gt; <span style="color:red">setEvent</span>(res.data));
  }, [id]);

  if (!<span style="color:red">event</span>) return &lt;Container className="mt-3"&gt;<span style="color:red">Loading...</span>&lt;/Container&gt;;

  return (
    &lt;Container className="mt-3"&gt;
      &lt;h2&gt;&lt;b&gt;<span style="color:red">VIEW DETAILS</span>&lt;/b&gt;&lt;/h2&gt;
      &lt;div className="mt-4 ms-4"&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Title</span>:&lt;/b&gt; {event.title}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Description</span>:&lt;/b&gt; {event.description}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Category</span>:&lt;/b&gt; {event.category}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Date</span>:&lt;/b&gt; {event.eventDate}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Seats</span>:&lt;/b&gt; {event.seats}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Online</span>:&lt;/b&gt; {event.online ? "<span style="color:red">Yes</span>" : "<span style="color:red">No</span>"}&lt;/p&gt;
        &lt;p&gt;&lt;b&gt;<span style="color:red">Level</span>:&lt;/b&gt; {event.level}&lt;/p&gt;
        &lt;p&gt;&lt;img src={event.bannerUrl} alt={event.title} style={{ width: <span style="color:red">260</span>, height: <span style="color:red">140</span>, objectFit: "<span style="color:red">cover</span>" }} /&gt;&lt;/p&gt;
      &lt;/div&gt;
      &lt;Button variant="outline-primary" onClick={() =&gt; navigate("/")}&gt;<span style="color:red">Back</span>&lt;/Button&gt;
    &lt;/Container&gt;
  );
}

export default <span style="color:red">EventDetail</span>;</code></pre>

### Điểm mấu chốt Đề 5 cần nhớ:

- Khác Đề 1-4: có thêm `search + sort + pagination` trong cùng màn hình list.
- `GET /api/events/search` trả `Page`, nên phải đọc `res.data.content`, `res.data.number`, `res.data.totalPages`.
- Nút submit dùng chung cho Add/Update qua `editId`.
- Sau create/update/delete luôn gọi lại `loadEvents(page, keyword, sort)` để đồng bộ bảng.
- Trước khi nộp: test cả 2 nhánh có keyword và không keyword.
- Khi đổi sang đề khác, ưu tiên thay toàn bộ token màu đỏ trước, sau đó mới test logic.

---

# BẢNG SO SÁNH 5 ĐỀ

|                  | Đề 1 (EMP) | Đề 2 (PRM)     | Đề 3 (STM)   | Đề 4 (CRM) | Đề 5 (EVM)     |
| ---------------- | ---------- | -------------- | ------------ | ---------- | -------------- |
| **Entity**       | Employee   | Product        | Student      | Course     | Event          |
| **TextBox**      | ✅         | ✅             | ✅           | ✅         | ✅             |
| **DropList**     | ✅         | ✅             | ✅           | ✅         | ✅             |
| **CheckBox**     | —          | ✅ inStock     | —            | ✅ active  | ✅ online      |
| **Radio**        | —          | —              | ✅ gender    | —          | ✅ level       |
| **TextArea**     | —          | ✅ description | —            | —          | ✅ description |
| **Date**         | —          | —              | ✅ birthDate | —          | ✅ eventDate   |
| **Image**        | —          | —              | ✅ avatarUrl | —          | ✅ bannerUrl   |
| **Search**       | —          | —              | —            | —          | ✅ keyword     |
| **Sort**         | —          | —              | —            | —          | ✅ date/name   |
| **Pagination**   | —          | —              | —            | —          | ✅             |
| **PUT (Update)** | —          | —              | —            | ✅         | ✅             |
| **Nút Edit**     | —          | —              | —            | ✅         | ✅             |
| **Cancel**       | —          | —              | —            | ✅         | ✅             |
| **Độ khó**       | ⭐         | ⭐⭐           | ⭐⭐         | ⭐⭐⭐     | ⭐⭐⭐⭐       |

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
