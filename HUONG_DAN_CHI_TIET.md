# Hướng Dẫn Chi Tiết: Book Shop Management (BSM) - Practical Exam SBA301

---

## Mục Lục

1. [Tổng Quan Dự Án](#1-tổng-quan-dự-án)
2. [Cấu Trúc Thư Mục](#2-cấu-trúc-thư-mục)
3. [Cấu Hình Backend (Spring Boot)](#3-cấu-hình-backend-spring-boot)
4. [Tạo Dự Án Frontend (React + Vite)](#4-tạo-dự-án-frontend-react--vite)
5. [Cài Đặt Dependencies](#5-cài-đặt-dependencies)
6. [Cấu Hình Vite](#6-cấu-hình-vite)
7. [File jsconfig.json](#7-file-jsconfigjson)
8. [File main.jsx - Entry Point](#8-file-mainjsx---entry-point)
9. [File App.jsx - Routing](#9-file-appjsx---routing)
10. [ShopService.js - Gọi API](#10-shopservicejs---gọi-api)
11. [ShopList.jsx - Màn Hình Chính](#11-shoplistjsx---màn-hình-chính)
12. [ShopDetail.jsx - Màn Hình Chi Tiết](#12-shopdetailjsx---màn-hình-chi-tiết)
13. [Cách Chạy Dự Án](#13-cách-chạy-dự-án)
14. [Checklist Kiểm Tra Trước Khi Nộp](#14-checklist-kiểm-tra-trước-khi-nộp)

---

## 1. Tổng Quan Dự Án

### Yêu cầu đề bài:

- **Tên dự án**: Book Shop Management
- **Mã đề**: BSM
- **Frontend**: React JS + React-Bootstrap + React-Router-Dom + axios + Vite (JavaScript)
- **Backend**: Spring Boot REST API chạy ở cổng **8080**
- **Frontend port**: **5173** (mặc định của Vite)
- **Database**: SBA301_2025_PE (dùng H2 in-memory)
- **Base API URL**: `http://localhost:8080/myapp/shops`

### Các API Endpoint:

| #   | Method | URL                  | Mô tả               |
| --- | ------ | -------------------- | ------------------- |
| 1   | GET    | `/myapp/shops`       | Lấy tất cả shop     |
| 2   | POST   | `/myapp/shops`       | Tạo shop mới        |
| 3   | PUT    | `/myapp/shops/{id}`  | Cập nhật shop       |
| 4   | DELETE | `/myapp/shops/{id}`  | Xóa shop            |
| 5   | GET    | `/myapp/shops/types` | Lấy danh sách type  |
| 6   | GET    | `/myapp/shops/{id}`  | Lấy chi tiết 1 shop |

### Các màn hình cần làm:

1. **Screen 1 - List Screen**: Form nhập liệu + bảng danh sách shop
2. **Screen 2 - Confirmation**: Dialog xác nhận xóa
3. **Screen 3 - Detail Screen**: Hiển thị chi tiết shop

---

## 2. Cấu Trúc Thư Mục

```
Practice1/
├── backend-pe/                    ← Spring Boot (đã có sẵn)
│   └── src/main/resources/
│       └── application.yaml       ← Cần sửa port thành 8080
│
└── SE1805_SE180211_BSM_PE/        ← React + Vite (tạo mới)
    ├── jsconfig.json              ← Bắt buộc (yêu cầu 3.8)
    ├── vite.config.js             ← Cấu hình Vite
    ├── index.html                 ← Title: "Book Shop Management"
    ├── package.json
    └── src/
        ├── main.jsx               ← Entry point
        ├── App.jsx                ← Routing
        ├── services/
        │   └── ShopService.js     ← Gọi API bằng axios
        └── components/
            ├── ShopList.jsx       ← Màn hình chính (Screen 1 + 2)
            └── ShopDetail.jsx     ← Màn hình chi tiết (Screen 3)
```

### Quy tắc đặt tên thư mục (B.3 mục 3.1):

Tên thư mục frontend **BẮT BUỘC** theo convention:

```
<ClassName>_<StudentID>_<ProjectCode>_PE
```

| Thành phần  | Giá trị                 | Ví dụ    |
| ----------- | ----------------------- | -------- |
| ClassName   | Tên lớp                 | SE1805   |
| StudentID   | Mã sinh viên            | SE180211 |
| ProjectCode | Mã đề bài               | BSM      |
| PE          | Viết tắt Practical Exam | PE       |

→ Kết quả: `SE1805_SE180211_BSM_PE`

---

## 3. Cấu Hình Backend (Spring Boot)

### File: `backend-pe/src/main/resources/application.yaml`

**Thay đổi duy nhất**: Sửa `server.port` từ `9999` thành `8080`.

```yaml
spring:
  application:
    name: backend-pe
  jpa:
    hibernate:
      ddl-auto: create
  h2:
    console:
      enabled: true
      path: /h2-console
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:SBA301_2025_PE
    username: sa
    password:
server:
  port: 8080 # ← SỬA TỪ 9999 THÀNH 8080
```

### Giải thích từng dòng:

| Dòng                         | Ý nghĩa                                             |
| ---------------------------- | --------------------------------------------------- |
| `ddl-auto: create`           | Mỗi lần chạy sẽ tạo lại bảng (phù hợp H2 in-memory) |
| `jdbc:h2:mem:SBA301_2025_PE` | Tên database là `SBA301_2025_PE`, chạy trong bộ nhớ |
| `username: sa`               | Username mặc định của H2                            |
| `password:`                  | Không đặt mật khẩu (để trống)                       |
| `port: 8080`                 | **BẮT BUỘC** theo đề bài (mục B.5)                  |

### Lưu ý:

- **KHÔNG sửa** bất kỳ file Java nào ở backend. Mọi thứ đã được code sẵn.
- Backend đã có `@CrossOrigin(origins = "http://localhost:5173")` trong controller, cho phép frontend gọi API.

---

## 4. Tạo Dự Án Frontend (React + Vite)

### Lệnh tạo dự án:

```bash
cd <đường_dẫn_đến_Practice1>
npm create vite@latest SE1805_SE180211_BSM_PE -- --template react
```

### Giải thích:

| Phần lệnh                | Ý nghĩa                                                 |
| ------------------------ | ------------------------------------------------------- |
| `npm create vite@latest` | Sử dụng npm để tạo project bằng Vite phiên bản mới nhất |
| `SE1805_SE180211_BSM_PE` | Tên thư mục dự án (theo convention B.3 mục 3.1)         |
| `--`                     | Phân cách giữa npm args và Vite args                    |
| `--template react`       | Chọn template React (JavaScript, không phải TypeScript) |

### Lưu ý:

- Khi được hỏi "Use Vite 8 beta?" → chọn **No**
- Đề bài yêu cầu dùng **JavaScript** (không phải TypeScript), nên chọn template `react` chứ KHÔNG phải `react-ts`

---

## 5. Cài Đặt Dependencies

### Lệnh cài đặt:

```bash
cd SE1805_SE180211_BSM_PE
npm install
npm install react-bootstrap bootstrap axios react-router-dom
```

### Giải thích từng package:

| Package            | Vai trò                                         | Lý do cài                                        |
| ------------------ | ----------------------------------------------- | ------------------------------------------------ |
| `react-bootstrap`  | Bộ component UI (Button, Table, Form, Modal...) | Đề bài yêu cầu dùng React-Bootstrap              |
| `bootstrap`        | CSS framework (react-bootstrap cần nó)          | Để import `bootstrap/dist/css/bootstrap.min.css` |
| `axios`            | HTTP client gọi REST API                        | Đề bài yêu cầu dùng axios                        |
| `react-router-dom` | Routing trong React (chuyển trang)              | Đề bài yêu cầu, cần để chuyển từ List → Detail   |

### Lưu ý:

- **KHÔNG** cài thêm bất kỳ thư viện nào khác. Đề bài ghi rõ: _"Note: Do not allow use other libraries"_
- Các thư viện được phép: React JS, React-Router-Dom, React-Bootstrap, axios, Vite

---

## 6. Cấu Hình Vite

### File: `SE1805_SE180211_BSM_PE/vite.config.js`

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

### Giải thích:

| Dòng                                       | Ý nghĩa                                                               |
| ------------------------------------------ | --------------------------------------------------------------------- |
| `import { defineConfig } from 'vite'`      | Import hàm `defineConfig` từ Vite để có auto-complete khi viết config |
| `import react from '@vitejs/plugin-react'` | Plugin giúp Vite hiểu và biên dịch JSX (cú pháp React)                |
| `plugins: [react()]`                       | Kích hoạt plugin React cho Vite                                       |
| `server: { port: 5173 }`                   | Đặt port dev server là 5173 (đề bài yêu cầu mục B.4, dòng 1)          |

### Lưu ý:

- 5173 là port mặc định của Vite, nhưng ta cấu hình tường minh để đảm bảo
- Nếu port 5173 bị chiếm, Vite sẽ tự động chuyển sang port khác → phải tắt ứng dụng đang chiếm port đó

---

## 7. File jsconfig.json

### File: `SE1805_SE180211_BSM_PE/jsconfig.json`

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

### Giải thích:

| Thuộc tính                 | Ý nghĩa                                            |
| -------------------------- | -------------------------------------------------- |
| `target: "ES6"`            | Mã JavaScript sẽ được biên dịch theo chuẩn ES6     |
| `module: "ESNext"`         | Sử dụng hệ thống module mới nhất (import/export)   |
| `moduleResolution: "Node"` | Giải quyết đường dẫn module theo kiểu Node.js      |
| `jsx: "react-jsx"`         | Cho phép sử dụng JSX trong file .jsx               |
| `baseUrl: "./src"`         | Đường dẫn gốc khi import (không ảnh hưởng runtime) |
| `include: ["src"]`         | Chỉ áp dụng cho thư mục src                        |

### Lưu ý:

- **BẮT BUỘC phải có file này** (yêu cầu 3.8: "Project folder must contain jsconfig.json file")
- File này giúp VS Code hiểu cấu trúc project → hỗ trợ IntelliSense tốt hơn

---

## 7.5. File index.html - Sửa Title

### File: `SE1805_SE180211_BSM_PE/index.html`

Vite tạo sẵn file `index.html` với title mặc định. Ta cần sửa title cho phù hợp:

```html
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Book Shop Management</title>
    <!-- SỬA TITLE -->
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.jsx"></script>
  </body>
</html>
```

### Giải thích:

| Dòng                                         | Ý nghĩa                                                                                       |
| -------------------------------------------- | --------------------------------------------------------------------------------------------- |
| `<title>Book Shop Management</title>`        | Title hiển thị trên tab trình duyệt. Sửa từ "frontend-pe" thành tên ứng dụng                  |
| `<div id="root"></div>`                      | Thẻ div mà React sẽ render vào (được gọi trong `main.jsx`: `document.getElementById('root')`) |
| `<script type="module" src="/src/main.jsx">` | Load file entry point của React                                                               |

### Lưu ý:

- **Phải sửa title** - nếu để mặc định "frontend-pe" sẽ không chuyên nghiệp và không khớp tên ứng dụng

---

## 8. File main.jsx - Entry Point

### File: `SE1805_SE180211_BSM_PE/src/main.jsx`

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

### Giải thích từng dòng:

| Dòng                                               | Ý nghĩa                                                                                                 |
| -------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| `import React from 'react'`                        | Import React (cần thiết cho JSX)                                                                        |
| `import { createRoot } from 'react-dom/client'`    | API mới của React 18+ để render app vào DOM                                                             |
| `import 'bootstrap/dist/css/bootstrap.min.css'`    | **BẮT BUỘC** - Import CSS của Bootstrap toàn cục. Nếu thiếu dòng này, react-bootstrap sẽ không có style |
| `import App from './App.jsx'`                      | Import component gốc App                                                                                |
| `import { BrowserRouter } from 'react-router-dom'` | Import BrowserRouter để bọc toàn bộ app, cung cấp context cho routing                                   |
| `createRoot(...)`                                  | Tạo root node cho React tại thẻ `<div id="root">` trong index.html                                      |
| `<React.StrictMode>`                               | Bật chế độ kiểm tra nghiêm ngặt (phát hiện bug tiềm ẩn)                                                 |
| `<BrowserRouter>`                                  | **PHẢI bọc ở đây** vì tất cả component con cần dùng routing (useNavigate, Routes...)                    |

### Lưu ý:

- `BrowserRouter` phải bọc NGOÀI `<App />` - nếu bọc trong App sẽ gây lỗi
- Import bootstrap CSS ở đây 1 lần duy nhất, không cần import lại ở các component con
- **KHÔNG import** `index.css` hay `App.css` (file gốc của Vite template) vì không cần

---

## 9. File App.jsx - Routing

### File: `SE1805_SE180211_BSM_PE/src/App.jsx`

```jsx
import { Routes, Route } from "react-router-dom";
import ShopList from "./components/ShopList";
import ShopDetail from "./components/ShopDetail";

function App() {
  return (
    <Routes>
      <Route path="/" element={<ShopList />} />
      <Route path="/shop/:id" element={<ShopDetail />} />
    </Routes>
  );
}

export default App;
```

### Giải thích:

| Dòng                                                  | Ý nghĩa                                                                         |
| ----------------------------------------------------- | ------------------------------------------------------------------------------- |
| `import { Routes, Route } from 'react-router-dom'`    | `Routes` là container chứa các `Route`, `Route` định nghĩa 1 đường dẫn          |
| `<Routes>`                                            | Bọc tất cả Route. Chỉ render Route đầu tiên khớp với URL hiện tại               |
| `<Route path="/" element={<ShopList />} />`           | Khi URL là `/` (trang chủ) → render component `ShopList`                        |
| `<Route path="/shop/:id" element={<ShopDetail />} />` | Khi URL là `/shop/1`, `/shop/2`... → render `ShopDetail`. `:id` là tham số động |

### Tại sao cấu trúc routing như vậy?

- Đề bài có 2 trang chính:
  - **Trang chủ** (`/`): Hiển thị form + bảng danh sách (Screen 1)
  - **Trang chi tiết** (`/shop/:id`): Hiển thị chi tiết 1 shop (Screen 3)
- Screen 2 (Confirmation) là Modal dialog, nằm trong ShopList, KHÔNG phải trang riêng

### Lưu ý:

- `:id` trong path là **route parameter**. Component con dùng `useParams()` để lấy giá trị
- `element` nhận JSX element (React v6+), KHÔNG phải `component={ShopList}` (cú pháp cũ v5)

---

## 10. ShopService.js - Gọi API

### File: `SE1805_SE180211_BSM_PE/src/services/ShopService.js`

```javascript
import axios from "axios";

const API_URL = "http://localhost:8080/myapp/shops";

const getAll = () => axios.get(API_URL);

const getById = (id) => axios.get(`${API_URL}/${id}`);

const create = (shop) => axios.post(API_URL, shop);

const deleteShop = (id) => axios.delete(`${API_URL}/${id}`);

const getTypes = () => axios.get(`${API_URL}/types`);

export default { getAll, getById, create, deleteShop, getTypes };
```

### Giải thích từng hàm:

| Hàm              | HTTP Method | URL                  | Mô tả                                      |
| ---------------- | ----------- | -------------------- | ------------------------------------------ |
| `getAll()`       | GET         | `/myapp/shops`       | Lấy tất cả shop từ DB                      |
| `getById(id)`    | GET         | `/myapp/shops/{id}`  | Lấy chi tiết 1 shop theo id                |
| `create(shop)`   | POST        | `/myapp/shops`       | Tạo shop mới, gửi object shop trong body   |
| `deleteShop(id)` | DELETE      | `/myapp/shops/{id}`  | Xóa shop theo id                           |
| `getTypes()`     | GET         | `/myapp/shops/types` | Lấy danh sách các loại shop (cho dropdown) |

### Giải thích cú pháp:

| Cú pháp                  | Ý nghĩa                                                                               |
| ------------------------ | ------------------------------------------------------------------------------------- |
| `axios.get(url)`         | Gửi HTTP GET request. Trả về Promise chứa response                                    |
| `axios.post(url, data)`  | Gửi HTTP POST request. `data` được tự động chuyển thành JSON                          |
| `axios.delete(url)`      | Gửi HTTP DELETE request                                                               |
| `` `${API_URL}/${id}` `` | Template literal - nối chuỗi URL với id. Ví dụ: `http://localhost:8080/myapp/shops/1` |
| `export default { ... }` | Export object chứa tất cả hàm, cho phép import bằng `import ShopService from '...'`   |

### Lưu ý:

- Tất cả hàm axios trả về **Promise**. Khi gọi phải dùng `.then()` hoặc `async/await`
- axios tự động set header `Content-Type: application/json` khi gửi POST
- Response data nằm trong `res.data` (axios bọc response trong object có thuộc tính `data`)
- Tên hàm `deleteShop` thay vì `delete` vì `delete` là từ khóa JavaScript

---

## 11. ShopList.jsx - Màn Hình Chính

### File: `SE1805_SE180211_BSM_PE/src/components/ShopList.jsx`

Đây là component phức tạp nhất, bao gồm:

- Form nhập liệu (Shop name, Open time, Owner, Type)
- Bảng hiển thị danh sách shop
- Modal xác nhận xóa

### Phân tích code theo từng phần:

---

### 11.1 Import

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
import ShopService from "../services/ShopService";
```

| Import                                            | Ý nghĩa                                                      |
| ------------------------------------------------- | ------------------------------------------------------------ |
| `useState`                                        | React Hook để quản lý state (biến thay đổi → re-render)      |
| `useEffect`                                       | React Hook để chạy side-effect (gọi API khi component mount) |
| `Table, Button, Form, Modal, Container, Row, Col` | Các component UI từ react-bootstrap                          |
| `Link`                                            | Component tạo hyperlink chuyển trang (react-router-dom)      |
| `ShopService`                                     | Service chứa các hàm gọi API                                 |

---

### 11.2 State Variables

```jsx
const [shops, setShops] = useState([]); // Danh sách shop
const [types, setTypes] = useState([]); // Danh sách type cho dropdown
const [name, setName] = useState(""); // Giá trị input Shop name
const [openTime, setOpenTime] = useState(""); // Giá trị input Open time
const [owner, setOwner] = useState(""); // Giá trị input Owner
const [type, setType] = useState(""); // Giá trị select Type

const [showConfirm, setShowConfirm] = useState(false); // Hiện/ẩn modal xác nhận
const [deleteTarget, setDeleteTarget] = useState(null); // Shop đang muốn xóa
```

### Giải thích `useState`:

```jsx
const [shops, setShops] = useState([]);
//     ↑         ↑              ↑
//     |         |              └── Giá trị khởi tạo (mảng rỗng)
//     |         └── Hàm để cập nhật giá trị (gọi → component re-render)
//     └── Giá trị hiện tại của state
```

| State                   | Giá trị khởi tạo | Lý do                           |
| ----------------------- | ---------------- | ------------------------------- |
| `shops`                 | `[]`             | Ban đầu chưa có data, mảng rỗng |
| `types`                 | `[]`             | Chưa fetch từ API               |
| `name, openTime, owner` | `''`             | Input rỗng                      |
| `type`                  | `''`             | Dropdown chưa chọn              |
| `showConfirm`           | `false`          | Modal ẩn ban đầu                |
| `deleteTarget`          | `null`           | Chưa chọn shop nào để xóa       |

---

### 11.3 useEffect - Gọi API khi trang load

```jsx
useEffect(() => {
  loadShops();
  loadTypes();
}, []);
```

| Phần                           | Ý nghĩa                                                                                            |
| ------------------------------ | -------------------------------------------------------------------------------------------------- |
| `useEffect(() => { ... }, [])` | Chạy hàm callback **1 lần duy nhất** khi component mount (tương tự `componentDidMount`)            |
| `[]` (dependency array rỗng)   | **QUAN TRỌNG**: Mảng rỗng = chỉ chạy 1 lần. Nếu không có `[]`, sẽ chạy sau MỖI lần render (vô hạn) |
| `loadShops()`                  | Gọi API lấy danh sách shop                                                                         |
| `loadTypes()`                  | Gọi API lấy danh sách type cho dropdown                                                            |

→ **Đáp ứng yêu cầu A.1**: "Khi user vào trang, tất cả shop trong database sẽ được hiển thị"

---

### 11.4 Hàm loadShops và loadTypes

```jsx
const loadShops = () => {
  ShopService.getAll().then((res) => setShops(res.data));
};

const loadTypes = () => {
  ShopService.getTypes().then((res) => setTypes(res.data));
};
```

| Cú pháp               | Ý nghĩa                                                                        |
| --------------------- | ------------------------------------------------------------------------------ |
| `.then((res) => ...)` | Khi Promise resolve (API trả về thành công), chạy callback                     |
| `res.data`            | Data thực tế từ server (axios bọc trong object `{ data, status, headers... }`) |
| `setShops(res.data)`  | Cập nhật state `shops` → component re-render → bảng hiển thị data mới          |

---

### 11.5 Hàm handleAddNew - Thêm shop mới

```jsx
const handleAddNew = () => {
  // Validate
  if (!name.trim()) {
    alert("Shop name is required");
    return;
  }
  if (name.trim().length > 50) {
    alert("Shop name must be at most 50 characters");
    return;
  }
  if (!openTime) {
    alert("Open time is required");
    return;
  }
  const openTimeNum = Number(openTime);
  if (isNaN(openTimeNum) || openTimeNum <= 0 || openTimeNum >= 12) {
    alert("Open time must be greater than 0 and less than 12");
    return;
  }
  if (!owner.trim()) {
    alert("Owner is required");
    return;
  }
  if (owner.trim().length > 100) {
    alert("Owner must be at most 100 characters");
    return;
  }
  if (!type) {
    alert("Type is required");
    return;
  }

  // Tạo object shop
  const shop = {
    name: name.trim(),
    openTime: openTimeNum,
    owner: owner.trim(),
    type,
  };

  // Gọi API
  ShopService.create(shop)
    .then(() => {
      alert("Created new Shop successfully");
      loadShops(); // Refresh danh sách
      // Reset form
      setName("");
      setOpenTime("");
      setOwner("");
      setType("");
    })
    .catch((err) => {
      alert(err.response?.data?.message || "Error creating shop");
    });
};
```

### Giải thích validation (theo Screen Definition):

| Validation                                | Field     | Điều kiện                                                   | Yêu cầu đề bài |
| ----------------------------------------- | --------- | ----------------------------------------------------------- | -------------- |
| `!name.trim()`                            | Shop Name | Bắt buộc (Mandatory: Yes)                                   | Field #1       |
| `name.trim().length > 50`                 | Shop Name | Tối đa 50 ký tự (Length: 50)                                | Field #1       |
| `!openTime`                               | Open time | Bắt buộc (Mandatory: Yes)                                   | Field #2       |
| `openTimeNum <= 0 \|\| openTimeNum >= 12` | Open time | > 0 và < 12 (Description: Greater than 0 and less than <12) | Field #2       |
| `!owner.trim()`                           | Owner     | Bắt buộc (Mandatory: Yes)                                   | Field #3       |
| `owner.trim().length > 100`               | Owner     | Tối đa 100 ký tự (Length: 100)                              | Field #3       |
| `!type`                                   | Type      | Bắt buộc (Mandatory: Yes)                                   | Field #4       |

### Giải thích cú pháp:

| Cú pháp                       | Ý nghĩa                                                      |
| ----------------------------- | ------------------------------------------------------------ |
| `name.trim()`                 | Xóa khoảng trắng đầu/cuối chuỗi                              |
| `!name.trim()`                | `true` nếu chuỗi rỗng hoặc chỉ có khoảng trắng               |
| `Number(openTime)`            | Chuyển chuỗi thành số                                        |
| `isNaN(openTimeNum)`          | Kiểm tra có phải NaN (Not a Number) không                    |
| `{ name: name.trim(), ... }`  | Tạo object JS gửi lên server                                 |
| `.catch((err) => ...)`        | Bắt lỗi khi API trả về error (ví dụ: shop name trùng)        |
| `err.response?.data?.message` | Optional chaining - truy xuất an toàn, không bị lỗi nếu null |

→ **Đáp ứng yêu cầu A.2**: Validate → gọi API → show "Created new Shop successfully" → hiển thị record mới

---

### 11.6 Hàm Delete - Xóa shop

```jsx
const handleDeleteClick = (shop) => {
  setDeleteTarget(shop); // Lưu shop muốn xóa
  setShowConfirm(true); // Hiện modal xác nhận
};

const handleDeleteConfirm = () => {
  ShopService.deleteShop(deleteTarget.id).then(() => {
    alert("Deleted successfully");
    loadShops(); // Refresh danh sách
    setShowConfirm(false); // Ẩn modal
    setDeleteTarget(null);
  });
};

const handleDeleteClose = () => {
  setShowConfirm(false); // Ẩn modal
  setDeleteTarget(null);
};
```

### Flow xóa shop:

1. User click "Delete" → `handleDeleteClick(shop)` → lưu shop + hiện Modal
2. Modal hiện "Are you sure you want to delete 'Tien Phong....'?"
3. User click "Yes" → `handleDeleteConfirm()` → gọi API DELETE → alert "Deleted successfully" → refresh list
4. User click "Close" → `handleDeleteClose()` → đóng Modal, không xóa

→ **Đáp ứng yêu cầu A.3**: Click Delete → dialog confirm → Yes: xóa + thông báo + cập nhật list | No: đóng dialog

---

### 11.7 Form UI

```jsx
<Form>
  <Form.Group as={Row} className="mb-2">
    <Form.Label column sm={2} className="text-end">
      Shop name:
    </Form.Label>
    <Col sm={10}>
      <Form.Control
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
        maxLength={50}
      />
    </Col>
  </Form.Group>
  ...
</Form>
```

### Giải thích React-Bootstrap component:

| Component                                   | Ý nghĩa                                                                 |
| ------------------------------------------- | ----------------------------------------------------------------------- |
| `<Container>`                               | Bọc ngoài, tạo margin 2 bên (giống `<div class="container">` Bootstrap) |
| `<Form>`                                    | Form container                                                          |
| `<Form.Group as={Row}>`                     | Một dòng trong form, render thành `<div class="row">`                   |
| `<Form.Label column sm={2}>`                | Label chiếm 2/12 cột, căn phải (`text-end`)                             |
| `<Col sm={10}>`                             | Input chiếm 10/12 cột                                                   |
| `<Form.Control>`                            | Input field (render thành `<input>`)                                    |
| `<Form.Select>`                             | Dropdown select (render thành `<select>`)                               |
| `value={name}`                              | Controlled component - giá trị input được quản lý bởi state             |
| `onChange={(e) => setName(e.target.value)}` | Khi user gõ → cập nhật state → re-render input                          |
| `maxLength={50}`                            | Giới hạn ký tự trên HTML (thêm lớp bảo vệ UI)                           |

### Tại sao dùng Controlled Component?

- React quản lý giá trị input qua state
- Khi cần validate hoặc lấy giá trị → đọc từ state, không cần `document.getElementById`
- Đảm bảo UI luôn đồng bộ với data

---

### 11.8 Table hiển thị danh sách

```jsx
<Table bordered hover>
  <thead>
    <tr>
      <th># No</th>
      <th>Shop Name</th>
      <th>Type</th>
      <th>Owner</th>
      <th>Open time</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    {shops.map((shop, index) => (
      <tr key={shop.id}>
        <td>{String(index + 1).padStart(2, "0")}</td>
        <td>{shop.name}</td>
        <td>{shop.type}</td>
        <td>{shop.owner}</td>
        <td>{shop.openTime}</td>
        <td>
          <Button
            variant="danger"
            size="sm"
            onClick={() => handleDeleteClick(shop)}
          >
            Delete
          </Button>
          {" | "}
          <Link to={`/shop/${shop.id}`}>View</Link>
        </td>
      </tr>
    ))}
  </tbody>
</Table>
```

### Giải thích:

| Cú pháp                              | Ý nghĩa                                                                             |
| ------------------------------------ | ----------------------------------------------------------------------------------- |
| `<Table bordered hover>`             | Bảng có viền (`bordered`) và highlight khi hover                                    |
| `shops.map((shop, index) => ...)`    | Lặp qua mảng shops, render 1 `<tr>` cho mỗi shop                                    |
| `key={shop.id}`                      | **BẮT BUỘC** trong React khi render list. Giúp React nhận biết phần tử nào thay đổi |
| `String(index + 1).padStart(2, '0')` | Chuyển số thành chuỗi 2 chữ số: 1→"01", 2→"02" (theo mẫu đề bài)                    |
| `variant="danger"`                   | Nút đỏ (Bootstrap danger color)                                                     |
| `size="sm"`                          | Kích thước nhỏ                                                                      |
| `<Link to={...}>View</Link>`         | Hyperlink chuyển đến trang chi tiết (Screen Definition yêu cầu View là Hyperlink)   |

### Tại sao dùng `<Link>` thay vì `<Button>` cho View?

Đề bài Screen Definition ghi rõ ở dòng #6: **Type = Hyperlink**. Do đó:

- `<Link to={`/shop/${shop.id}`}>View</Link>` → render thành thẻ `<a>` (hyperlink thực sự)
- `<Button variant="link">` → render thành `<button>` (không phải hyperlink đúng nghĩa)

`<Link>` là component từ `react-router-dom`, hoạt động giống thẻ `<a>` nhưng không reload trang (SPA navigation).

### Thứ tự cột trong bảng:

Theo đúng Screen Layout: # No | Shop Name | Type | Owner | Open time | Action

---

### 11.9 Modal Xác Nhận Xóa (Screen 2)

```jsx
<Modal show={showConfirm} onHide={handleDeleteClose} centered>
  <Modal.Header closeButton>
    <Modal.Title>Confirmation</Modal.Title>
  </Modal.Header>
  <Modal.Body>
    Are you sure you want to delete "{deleteTarget?.name}...."?
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
```

### Giải thích:

| Prop/Component               | Ý nghĩa                                                              |
| ---------------------------- | -------------------------------------------------------------------- |
| `show={showConfirm}`         | Modal hiện khi `showConfirm` = `true`                                |
| `onHide={handleDeleteClose}` | Khi click ngoài modal hoặc nút X → đóng modal                        |
| `centered`                   | Modal hiện ở giữa màn hình                                           |
| `closeButton`                | Thêm nút X ở góc phải header                                         |
| `deleteTarget?.name`         | Hiển thị tên shop. `?.` để không bị lỗi khi `deleteTarget` là `null` |
| `variant="primary"`          | Nút xanh (Yes)                                                       |
| `variant="secondary"`        | Nút xám (Close)                                                      |

---

## 12. ShopDetail.jsx - Màn Hình Chi Tiết

### File: `SE1805_SE180211_BSM_PE/src/components/ShopDetail.jsx`

```jsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import ShopService from "../services/ShopService";

function ShopDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [shop, setShop] = useState(null);

  useEffect(() => {
    ShopService.getById(id).then((res) => setShop(res.data));
  }, [id]);

  if (!shop) return <Container className="mt-3">Loading...</Container>;

  return (
    <Container className="mt-3">
      <h2>
        <b>VIEW DETAILS</b>
      </h2>
      <div className="mt-4 ms-4">
        <p>
          <b>Shop Name:</b> {shop.name}
        </p>
        <p>
          <b>Owner:</b> {shop.owner}
        </p>
        <p>
          <b>Type:</b> {shop.type}
        </p>
        <p>
          <b>Open time:</b> {shop.openTime}
        </p>
      </div>
      <Button variant="outline-primary" onClick={() => navigate("/")}>
        Quay lai
      </Button>
    </Container>
  );
}

export default ShopDetail;
```

### Giải thích:

| Dòng                             | Ý nghĩa                                                      |
| -------------------------------- | ------------------------------------------------------------ |
| `const { id } = useParams()`     | Lấy tham số `id` từ URL. Nếu URL là `/shop/3` thì `id = "3"` |
| `const navigate = useNavigate()` | Hàm để chuyển trang                                          |
| `useState(null)`                 | Ban đầu shop = null (chưa fetch)                             |
| `useEffect(() => { ... }, [id])` | Gọi API khi `id` thay đổi. `[id]` = chạy lại khi id khác     |
| `if (!shop) return ...`          | Hiển thị "Loading..." trong lúc chờ API trả về               |
| `navigate('/')`                  | Quay về trang chủ (danh sách shop)                           |

### Thứ tự hiển thị (theo Screen 3):

1. Shop Name
2. Owner
3. Type
4. Open time

### Nút "Quay lai":

- `variant="outline-primary"` = viền xanh, nền trắng
- Click → `navigate('/')` → về trang chủ `/`

→ **Đáp ứng yêu cầu A.4**: Click "View" → hiển thị trang chi tiết với đầy đủ thông tin

---

## 13. Cách Chạy Dự Án

### Bước 1: Chạy Backend

```bash
cd backend-pe
./mvnw spring-boot:run
```

Hoặc trên Windows:

```bash
cd backend-pe
mvnw.cmd spring-boot:run
```

**Kiểm tra**: Mở trình duyệt, truy cập `http://localhost:8080/myapp/shops` → phải trả về `[]` (mảng rỗng)

### Bước 2: Chạy Frontend

```bash
cd SE1805_SE180211_BSM_PE
npm run dev
```

**Kết quả**: Terminal hiển thị:

```
  VITE v7.x.x  ready in xxx ms

  ➜  Local:   http://localhost:5173/
```

### Bước 3: Kiểm tra ứng dụng

1. Mở `http://localhost:5173/` → Thấy trang "Book Shop Management" với form + bảng rỗng
2. Nhập thông tin → Click "Add New" → Thấy alert "Created new Shop successfully" → Shop xuất hiện trong bảng
3. Click "Delete" → Modal xác nhận hiện ra → Click "Yes" → Alert "Deleted successfully" → Shop biến mất
4. Click "View" → Chuyển sang trang chi tiết → Click "Quay lai" → Về trang chính

---

## 14. Checklist Kiểm Tra Trước Khi Nộp

### Yêu cầu bắt buộc (vi phạm = 0 điểm):

| #   | Yêu cầu                                                        | Kiểm tra                    |
| --- | -------------------------------------------------------------- | --------------------------- |
| 3.2 | React JS, React-bootstrap, Vite, JavaScript                    | ✅ Đã đúng                  |
| 3.3 | Chỉ dùng React, React-Router-Dom, React-Bootstrap, axios, Vite | ✅ Không dùng thư viện khác |
| 3.4 | Tên component/attribute đúng theo yêu cầu                      | ✅                          |
| 3.6 | REST API chạy ở port 8080                                      | ✅ Đã sửa application.yaml  |
| 3.7 | Frontend chạy ở http://localhost:5173/                         | ✅ Vite config port 5173    |
| 3.8 | Có file jsconfig.json                                          | ✅ Đã tạo                   |
| 3.1 | Tên thư mục `<ClassName>_<StudentID>_<ProjectCode>_PE`         | ✅ SE1805_SE180211_BSM_PE   |

### Yêu cầu chức năng:

| #   | Yêu cầu                                             | Điểm | Trạng thái |
| --- | --------------------------------------------------- | ---- | ---------- |
| A.1 | Hiển thị tất cả shop khi vào trang                  | 2    | ✅         |
| A.2 | Add New: validate + call API + thông báo + hiển thị | 3    | ✅         |
| A.3 | Delete: dialog confirm + xóa + thông báo + cập nhật | 2    | ✅         |
| A.4 | View: chuyển trang chi tiết                         | 2    | ✅         |
| B   | Giao diện đúng layout + label + rõ ràng             | 1    | ✅         |

### Trước khi nộp bài:

1. **Xóa thư mục `node_modules`** trong `SE1805_SE180211_BSM_PE`
2. Nén (zip) toàn bộ thư mục project
3. Kiểm tra file zip có chứa cả `backend-pe` và `SE1805_SE180211_BSM_PE`

---

## Tổng Kết

### Các file đã tạo/sửa:

| File                                                   | Hành động                           |
| ------------------------------------------------------ | ----------------------------------- |
| `backend-pe/src/main/resources/application.yaml`       | Sửa port 9999 → 8080                |
| `SE1805_SE180211_BSM_PE/jsconfig.json`                 | Tạo mới                             |
| `SE1805_SE180211_BSM_PE/index.html`                    | Sửa title → "Book Shop Management"  |
| `SE1805_SE180211_BSM_PE/vite.config.js`                | Sửa thêm server.port                |
| `SE1805_SE180211_BSM_PE/src/main.jsx`                  | Sửa (BrowserRouter + Bootstrap CSS) |
| `SE1805_SE180211_BSM_PE/src/App.jsx`                   | Sửa (Routing)                       |
| `SE1805_SE180211_BSM_PE/src/services/ShopService.js`   | Tạo mới                             |
| `SE1805_SE180211_BSM_PE/src/components/ShopList.jsx`   | Tạo mới                             |
| `SE1805_SE180211_BSM_PE/src/components/ShopDetail.jsx` | Tạo mới                             |
