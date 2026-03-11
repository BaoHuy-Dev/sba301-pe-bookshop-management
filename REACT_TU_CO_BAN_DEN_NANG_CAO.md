# React JS - Từ Cơ Bản Đến Nâng Cao

> Tài liệu dành cho người mới bắt đầu, giải thích chi tiết từng khái niệm.

---

## Mục Lục

### Phần 1: Nền Tảng

1. [React là gì?](#1-react-là-gì)
2. [Tại sao dùng React?](#2-tại-sao-dùng-react)
3. [Các công cụ cần chuẩn bị](#3-các-công-cụ-cần-chuẩn-bị)
4. [Tạo dự án React với Vite](#4-tạo-dự-án-react-với-vite)
5. [Cấu trúc thư mục dự án](#5-cấu-trúc-thư-mục-dự-án)
6. [JSX là gì?](#6-jsx-là-gì)

### Phần 2: Component

7. [Component là gì?](#7-component-là-gì)
8. [Function Component vs Class Component](#8-function-component-vs-class-component)
9. [Props - Truyền dữ liệu vào Component](#9-props---truyền-dữ-liệu-vào-component)
10. [Children Props](#10-children-props)

### Phần 3: State & Hooks Cơ Bản

11. [useState - Quản lý trạng thái](#11-usestate---quản-lý-trạng-thái)
12. [useEffect - Side Effects](#12-useeffect---side-effects)
13. [Xử lý sự kiện (Event Handling)](#13-xử-lý-sự-kiện-event-handling)

### Phần 4: Render & Danh Sách

14. [Conditional Rendering](#14-conditional-rendering)
15. [Rendering Lists & Key](#15-rendering-lists--key)
16. [Form & Controlled Components](#16-form--controlled-components)

### Phần 5: Giao Tiếp API

17. [Gọi API với axios](#17-gọi-api-với-axios)
18. [Kết hợp useState + useEffect + axios](#18-kết-hợp-usestate--useeffect--axios)

### Phần 6: Routing

19. [React Router Dom](#19-react-router-dom)
20. [useNavigate & useParams](#20-usenavigate--useparams)

### Phần 7: UI Framework

21. [React-Bootstrap](#21-react-bootstrap)

### Phần 8: Hooks Nâng Cao

22. [useRef](#22-useref)
23. [useMemo & useCallback](#23-usememo--usecallback)
24. [useContext - Chia sẻ state toàn cục](#24-usecontext---chia-sẻ-state-toàn-cục)
25. [useReducer - State phức tạp](#25-usereducer---state-phức-tạp)
26. [Custom Hooks](#26-custom-hooks)

### Phần 9: Patterns Nâng Cao

27. [Component Composition](#27-component-composition)
28. [Lifting State Up](#28-lifting-state-up)
29. [Error Boundaries](#29-error-boundaries)

### Phần 10: Tổng Kết

30. [Tóm tắt toàn bộ kiến thức](#30-tóm-tắt-toàn-bộ-kiến-thức)

---

# PHẦN 1: NỀN TẢNG

---

## 1. React là gì?

**React** là một **thư viện JavaScript** (library) do Facebook (Meta) tạo ra, dùng để xây dựng **giao diện người dùng** (User Interface - UI).

### Hình dung đơn giản:

```
Trang web truyền thống:
  HTML → Cấu trúc
  CSS  → Trang trí
  JS   → Hành vi

Trang web React:
  React = HTML + CSS + JS gộp lại trong "Component"
```

### Ví dụ so sánh:

**HTML thuần:**

```html
<div id="greeting"></div>
<script>
  document.getElementById("greeting").innerHTML = "<h1>Xin chào</h1>";
</script>
```

**React:**

```jsx
function Greeting() {
  return <h1>Xin chào</h1>;
}
```

→ React ngắn gọn, dễ đọc, dễ bảo trì hơn rất nhiều.

### Đặc điểm chính:

- **Component-based**: Chia UI thành các mảnh nhỏ (component), mỗi mảnh quản lý riêng
- **Declarative**: Bạn mô tả UI muốn hiển thị, React tự lo cập nhật DOM
- **Virtual DOM**: React tạo bản sao DOM trong bộ nhớ, chỉ cập nhật phần thay đổi → nhanh

---

## 2. Tại sao dùng React?

| Lý do             | Giải thích                                                             |
| ----------------- | ---------------------------------------------------------------------- |
| **Tái sử dụng**   | Viết component 1 lần, dùng nhiều nơi                                   |
| **Nhanh**         | Virtual DOM chỉ cập nhật phần thay đổi, không render lại toàn bộ trang |
| **Cộng đồng lớn** | Thư viện phong phú, dễ tìm giải pháp                                   |
| **SPA**           | Single Page Application - chỉ 1 file HTML, chuyển trang không reload   |
| **Phổ biến**      | Được dùng bởi Facebook, Instagram, Netflix, Airbnb...                  |

### SPA là gì?

```
Trang web truyền thống (MPA - Multi Page App):
  Trang chủ → Click link → Server trả về HTML mới → Trình duyệt reload toàn bộ

SPA (Single Page App):
  Trang chủ → Click link → JavaScript thay đổi nội dung → KHÔNG reload trang
```

→ Trải nghiệm mượt mà như ứng dụng mobile.

---

## 3. Các công cụ cần chuẩn bị

### 3.1 Node.js

Node.js là môi trường chạy JavaScript bên ngoài trình duyệt. React cần Node.js để:

- Chạy Vite (build tool)
- Quản lý package qua npm

**Cài đặt**: Tải từ https://nodejs.org (chọn bản LTS)

**Kiểm tra đã cài:**

```bash
node -v    # Hiện phiên bản Node.js, ví dụ: v20.11.0
npm -v     # Hiện phiên bản npm, ví dụ: 10.2.4
```

### 3.2 npm là gì?

**npm** (Node Package Manager) = trình quản lý package (thư viện) của JavaScript.

```
npm install react        → Tải thư viện react về dự án
npm install axios        → Tải thư viện axios về dự án
npm run dev              → Chạy script "dev" trong package.json
```

### 3.3 Code Editor

Dùng **Visual Studio Code** (VS Code) - miễn phí, nhiều extension hỗ trợ React.

### 3.4 Vite là gì?

**Vite** (đọc: "vít") là **build tool** cho dự án JavaScript/React.

| Công cụ | Vai trò                                         |
| ------- | ----------------------------------------------- |
| Vite    | Biên dịch JSX → JS, chạy dev server, hot reload |
| npm     | Quản lý thư viện (package)                      |
| Node.js | Môi trường chạy Vite và npm                     |

**Tại sao Vite chứ không phải Create React App (CRA)?**

- Vite khởi động **cực nhanh** (< 1 giây)
- CRA đã lỗi thời (deprecated)
- Vite hỗ trợ Hot Module Replacement (HMR) - sửa code → thấy kết quả ngay lập tức

---

## 4. Tạo dự án React với Vite

### Bước 1: Mở Terminal, chạy lệnh

```bash
npm create vite@latest my-react-app -- --template react
```

### Giải thích từng phần:

| Phần               | Ý nghĩa                                 |
| ------------------ | --------------------------------------- |
| `npm create`       | Lệnh npm để tạo project mới từ template |
| `vite@latest`      | Sử dụng Vite phiên bản mới nhất         |
| `my-react-app`     | Tên thư mục dự án (đặt tùy ý)           |
| `--`               | Phân cách giữa npm args và Vite args    |
| `--template react` | Chọn template React + JavaScript        |

### Bước 2: Vào thư mục và cài dependencies

```bash
cd my-react-app
npm install
```

`npm install` đọc file `package.json` và tải tất cả thư viện cần thiết vào thư mục `node_modules/`.

### Bước 3: Chạy dự án

```bash
npm run dev
```

**Kết quả:**

```
  VITE v7.x.x  ready in 300 ms

  ➜  Local:   http://localhost:5173/
  ➜  Network: use --host to expose
```

→ Mở trình duyệt tại `http://localhost:5173/` để xem ứng dụng.

### npm run dev hoạt động thế nào?

```json
// package.json
{
  "scripts": {
    "dev": "vite", // npm run dev → chạy lệnh "vite"
    "build": "vite build", // npm run build → build production
    "preview": "vite preview"
  }
}
```

Khi gõ `npm run dev`, npm tìm trong `scripts` → thấy `"dev": "vite"` → chạy Vite dev server.

---

## 5. Cấu trúc thư mục dự án

```
my-react-app/
├── node_modules/          ← Chứa tất cả thư viện (KHÔNG sửa, KHÔNG commit)
├── public/                ← File tĩnh (ảnh, favicon...)
│   └── vite.svg
├── src/                   ← CODE CHÍNH - viết code ở đây
│   ├── assets/            ← Ảnh, font...
│   ├── App.jsx            ← Component gốc
│   ├── App.css            ← CSS cho App
│   ├── main.jsx           ← Entry point - nơi React bắt đầu
│   └── index.css          ← CSS toàn cục
├── index.html             ← File HTML duy nhất (SPA)
├── package.json           ← Danh sách dependencies + scripts
├── package-lock.json      ← Khóa phiên bản chính xác của dependencies
└── vite.config.js         ← Cấu hình Vite
```

### Giải thích từng file quan trọng:

#### `index.html`

```html
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>My App</title>
  </head>
  <body>
    <div id="root"></div>
    <!-- React render vào đây -->
    <script type="module" src="/src/main.jsx"></script>
    <!-- Load entry point -->
  </body>
</html>
```

→ Cả ứng dụng React chỉ có **1 file HTML** với **1 thẻ `<div id="root">`**. React sẽ "nhét" toàn bộ UI vào đây.

#### `src/main.jsx` (Entry Point)

```jsx
import React from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);
```

**Luồng hoạt động:**

1. `document.getElementById('root')` → Tìm thẻ `<div id="root">` trong `index.html`
2. `createRoot(...)` → Tạo root node cho React
3. `.render(<App />)` → Render component `App` vào root

#### `src/App.jsx` (Component gốc)

```jsx
function App() {
  return (
    <div>
      <h1>Hello React!</h1>
    </div>
  );
}

export default App;
```

→ Đây là component đầu tiên được render. Tất cả component khác sẽ nằm bên trong `App`.

#### `package.json`

```json
{
  "name": "my-react-app",
  "dependencies": {
    "react": "^19.2.0", // Thư viện React core
    "react-dom": "^19.2.0" // React kết nối với DOM (trình duyệt)
  },
  "devDependencies": {
    "vite": "^7.3.1", // Build tool
    "@vitejs/plugin-react": "^5.1.1" // Plugin React cho Vite
  }
}
```

| Loại              | Giải thích                                        |
| ----------------- | ------------------------------------------------- |
| `dependencies`    | Thư viện **cần khi chạy** ứng dụng (production)   |
| `devDependencies` | Thư viện **chỉ cần khi phát triển** (development) |

---

## 6. JSX là gì?

**JSX** (JavaScript XML) = cú pháp cho phép **viết HTML bên trong JavaScript**.

### Ví dụ:

```jsx
// Đây là JSX - trông như HTML nhưng nằm trong file .jsx
function Welcome() {
  return <h1>Xin chào thế giới!</h1>;
}
```

### JSX KHÔNG phải HTML. Có một số khác biệt:

| HTML                 | JSX                        | Lý do                                |
| -------------------- | -------------------------- | ------------------------------------ |
| `class="red"`        | `className="red"`          | `class` là từ khóa JavaScript        |
| `for="name"`         | `htmlFor="name"`           | `for` là từ khóa JavaScript          |
| `<br>`               | `<br />`                   | JSX yêu cầu tag tự đóng              |
| `style="color: red"` | `style={{ color: 'red' }}` | JSX dùng object cho style            |
| `onclick="func()"`   | `onClick={func}`           | camelCase, truyền function reference |

### Quy tắc JSX:

#### 1. Phải có 1 thẻ bao ngoài duy nhất

```jsx
// ❌ SAI - 2 thẻ ngang hàng
function Bad() {
  return (
    <h1>Tiêu đề</h1>
    <p>Nội dung</p>
  )
}

// ✅ ĐÚNG - bọc trong 1 thẻ div
function Good() {
  return (
    <div>
      <h1>Tiêu đề</h1>
      <p>Nội dung</p>
    </div>
  )
}

// ✅ ĐÚNG - dùng Fragment (không tạo thẻ HTML thừa)
function Better() {
  return (
    <>
      <h1>Tiêu đề</h1>
      <p>Nội dung</p>
    </>
  )
}
```

`<>...</>` gọi là **Fragment** - bọc nhiều thẻ mà không tạo thẻ HTML thừa trong DOM.

#### 2. Nhúng JavaScript vào JSX bằng `{}`

```jsx
function Greeting() {
  const name = "Nguyễn Văn A";
  const age = 20;

  return (
    <div>
      <h1>Xin chào {name}!</h1> {/* Hiển thị biến */}
      <p>Tuổi: {age}</p> {/* Hiển thị biến số */}
      <p>Năm sinh: {2026 - age}</p> {/* Biểu thức tính toán */}
      <p>Trạng thái: {age >= 18 ? "Người lớn" : "Trẻ em"}</p> {/* Ternary */}
    </div>
  );
}
```

**Quy tắc**: Bất kỳ **biểu thức JavaScript** nào cũng có thể đặt trong `{}`.

Biểu thức = thứ trả về giá trị: biến, phép tính, gọi hàm, ternary...
Câu lệnh ≠ biểu thức: `if`, `for`, `while` **KHÔNG** đặt trong `{}` được.

#### 3. Comment trong JSX

```jsx
function Demo() {
  return (
    <div>
      {/* Đây là comment trong JSX */}
      <h1>Hello</h1>
    </div>
  );
}
```

---

# PHẦN 2: COMPONENT

---

## 7. Component là gì?

**Component** = một "mảnh" độc lập của giao diện. Giống như mảnh ghép LEGO.

### Hình dung:

```
Trang web = nhiều component ghép lại

┌──────────────────────────────────┐
│  <Header />                      │  ← Component Header
├──────────────────────────────────┤
│  <Sidebar />  │  <MainContent /> │  ← Component Sidebar + MainContent
│               │                  │
│               │  <Card />        │  ← Component Card (nằm trong MainContent)
│               │  <Card />        │
│               │  <Card />        │
├──────────────────────────────────┤
│  <Footer />                      │  ← Component Footer
└──────────────────────────────────┘
```

### Tại sao chia thành Component?

1. **Tái sử dụng**: Component `<Card />` viết 1 lần, dùng 3 lần
2. **Dễ bảo trì**: Sửa Header → chỉ sửa file Header.jsx
3. **Dễ đọc**: Mỗi file chỉ lo 1 việc
4. **Dễ test**: Test từng component riêng lẻ

### Quy tắc đặt tên:

```jsx
// ✅ ĐÚNG - PascalCase (chữ cái đầu viết hoa)
function ShopList() { ... }
function UserProfile() { ... }

// ❌ SAI - camelCase hoặc chữ thường
function shopList() { ... }    // React sẽ hiểu đây là thẻ HTML, không phải component
function userprofile() { ... }
```

**BẮT BUỘC**: Tên component phải viết **PascalCase** (chữ cái đầu mỗi từ viết hoa).

### Cách tạo và sử dụng Component:

**Bước 1:** Tạo file `src/components/Greeting.jsx`

```jsx
function Greeting() {
  return <h1>Xin chào!</h1>;
}

export default Greeting; // Xuất component ra ngoài
```

**Bước 2:** Sử dụng trong `App.jsx`

```jsx
import Greeting from "./components/Greeting"; // Import vào

function App() {
  return (
    <div>
      <Greeting /> {/* Sử dụng như thẻ HTML */}
      <Greeting /> {/* Có thể dùng nhiều lần */}
    </div>
  );
}
```

---

## 8. Function Component vs Class Component

### Function Component (DÙNG CÁI NÀY):

```jsx
function Welcome() {
  return <h1>Xin chào</h1>;
}

// Hoặc viết bằng arrow function:
const Welcome = () => {
  return <h1>Xin chào</h1>;
};

// Hoặc ngắn hơn (implicit return):
const Welcome = () => <h1>Xin chào</h1>;
```

### Class Component (CŨ - KHÔNG NÊN DÙNG):

```jsx
import React from "react";

class Welcome extends React.Component {
  render() {
    return <h1>Xin chào</h1>;
  }
}
```

### So sánh:

|              | Function Component    | Class Component                                |
| ------------ | --------------------- | ---------------------------------------------- |
| Cú pháp      | Đơn giản, ngắn gọn    | Phức tạp, dài dòng                             |
| State        | Dùng `useState` Hook  | Dùng `this.state`                              |
| Lifecycle    | Dùng `useEffect` Hook | `componentDidMount`, `componentWillUnmount`... |
| Xu hướng     | **Chuẩn hiện tại**    | Lỗi thời                                       |
| React khuyên | ✅ Dùng               | ❌ Không khuyến khích                          |

→ Từ React 16.8 trở đi, **Function Component + Hooks** là cách viết chuẩn. Toàn bộ tài liệu này sẽ dùng Function Component.

---

## 9. Props - Truyền dữ liệu vào Component

**Props** (properties) = cách truyền dữ liệu từ component **cha** sang component **con**.

### Hình dung:

```
Component Cha truyền data → Component Con nhận qua props

<App>                              (Cha)
  └── <UserCard name="Hải" age={20} />   (Con - nhận props)
```

### Ví dụ cơ bản:

**Component Con** nhận props:

```jsx
// UserCard.jsx
function UserCard(props) {
  return (
    <div>
      <h2>{props.name}</h2>
      <p>Tuổi: {props.age}</p>
    </div>
  );
}

export default UserCard;
```

**Component Cha** truyền props:

```jsx
// App.jsx
import UserCard from "./components/UserCard";

function App() {
  return (
    <div>
      <UserCard name="Nguyễn Văn A" age={20} />
      <UserCard name="Trần Thị B" age={22} />
      <UserCard name="Lê Văn C" age={19} />
    </div>
  );
}
```

### Destructuring Props (cách viết gọn hơn):

```jsx
// Thay vì: function UserCard(props) rồi dùng props.name, props.age
// Ta viết:

function UserCard({ name, age }) {
  // Destructuring ngay ở parameter
  return (
    <div>
      <h2>{name}</h2>
      <p>Tuổi: {age}</p>
    </div>
  );
}
```

### Các loại giá trị props:

```jsx
<MyComponent
  name="Văn A" // String
  age={20} // Number (dùng {})
  isActive={true} // Boolean
  hobbies={["đọc", "viết"]} // Array
  user={{ name: "A" }} // Object (2 lớp {}: 1 cho JSX, 1 cho object)
  onClick={handleClick} // Function
/>
```

### Quy tắc quan trọng: Props là READ-ONLY

```jsx
function UserCard({ name }) {
  // ❌ SAI - KHÔNG ĐƯỢC sửa props
  name = "Tên khác";

  // ✅ ĐÚNG - Chỉ đọc
  return <h1>{name}</h1>;
}
```

→ Props giống **"hợp đồng"**: cha đưa gì thì con dùng nấy, con **không được sửa**.

---

## 10. Children Props

`children` là props đặc biệt - cho phép truyền **nội dung bên trong thẻ** component.

### Ví dụ:

```jsx
// Card.jsx - Component bọc ngoài
function Card({ title, children }) {
  return (
    <div
      style={{ border: "1px solid #ccc", padding: "16px", borderRadius: "8px" }}
    >
      <h3>{title}</h3>
      <div>{children}</div> {/* Nội dung bên trong */}
    </div>
  );
}

// App.jsx - Sử dụng
function App() {
  return (
    <div>
      <Card title="Thông tin">
        <p>Đây là nội dung bên trong Card</p> {/* children */}
        <button>Click</button> {/* children */}
      </Card>

      <Card title="Danh sách">
        <ul>
          {" "}
          {/* children */}
          <li>Item 1</li>
          <li>Item 2</li>
        </ul>
      </Card>
    </div>
  );
}
```

→ `children` = mọi thứ nằm giữa `<Card>` và `</Card>`.

### Ứng dụng: Layout Component

```jsx
function PageLayout({ children }) {
  return (
    <div>
      <header>Header chung</header>
      <main>{children}</main>       {/* Nội dung thay đổi theo trang */}
      <footer>Footer chung</footer>
    </div>
  )
}

// Trang 1:
<PageLayout>
  <h1>Trang chủ</h1>
</PageLayout>

// Trang 2:
<PageLayout>
  <h1>Giới thiệu</h1>
</PageLayout>
```

---

# PHẦN 3: STATE & HOOKS CƠ BẢN

---

## 11. useState - Quản lý trạng thái

### Hook là gì?

**Hook** = các hàm đặc biệt của React, bắt đầu bằng chữ "**use**".
Hook cho phép Function Component có tính năng mà trước đây chỉ Class Component mới có (state, lifecycle...).

### useState - Hook phổ biến nhất

`useState` cho phép component **nhớ** dữ liệu và **tự cập nhật UI** khi dữ liệu thay đổi.

### Cú pháp:

```jsx
import { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0);
  //     ↑        ↑              ↑
  //     |        |              └── Giá trị ban đầu (initial value)
  //     |        └── Hàm để cập nhật giá trị
  //     └── Giá trị hiện tại

  return (
    <div>
      <p>Đếm: {count}</p>
      <button onClick={() => setCount(count + 1)}>Tăng</button>
    </div>
  );
}
```

### Giải thích chi tiết:

```
Bước 1: Component render lần đầu
  → count = 0 (initial value)
  → Hiển thị: "Đếm: 0"

Bước 2: User click nút "Tăng"
  → setCount(0 + 1) được gọi
  → React ghi nhận: count mới = 1
  → React RE-RENDER component
  → Hiển thị: "Đếm: 1"

Bước 3: User click lần nữa
  → setCount(1 + 1) được gọi
  → count mới = 2
  → Re-render → "Đếm: 2"
```

### Tại sao không dùng biến thường?

```jsx
// ❌ SAI - UI sẽ KHÔNG cập nhật
function Counter() {
  let count = 0;

  const handleClick = () => {
    count = count + 1; // Biến thay đổi nhưng React KHÔNG BIẾT
    console.log(count); // Console in ra 1, 2, 3... nhưng UI vẫn hiện 0
  };

  return (
    <div>
      <p>Đếm: {count}</p> {/* LUÔN hiện 0 */}
      <button onClick={handleClick}>Tăng</button>
    </div>
  );
}
```

→ Biến thường thay đổi nhưng React **không biết** → không re-render → UI không đổi.
→ `useState` **báo cho React biết** data thay đổi → React re-render → UI cập nhật.

### Nhiều useState trong 1 component:

```jsx
function UserForm() {
  const [name, setName] = useState("");
  const [age, setAge] = useState(0);
  const [isActive, setIsActive] = useState(true);

  return (
    <div>
      <input value={name} onChange={(e) => setName(e.target.value)} />
      <button onClick={() => setAge(age + 1)}>Tăng tuổi: {age}</button>
      <button onClick={() => setIsActive(!isActive)}>
        {isActive ? "Active" : "Inactive"}
      </button>
    </div>
  );
}
```

### useState với Object:

```jsx
function UserForm() {
  const [user, setUser] = useState({ name: "", age: 0 });

  const updateName = (newName) => {
    // ❌ SAI - Mutate trực tiếp
    // user.name = newName

    // ✅ ĐÚNG - Tạo object mới (spread operator)
    setUser({ ...user, name: newName });
    //        ↑ Copy tất cả thuộc tính cũ, rồi ghi đè name
  };

  return (
    <div>
      <input value={user.name} onChange={(e) => updateName(e.target.value)} />
      <p>
        Tên: {user.name}, Tuổi: {user.age}
      </p>
    </div>
  );
}
```

**QUY TẮC VÀNG**: **KHÔNG BAO GIỜ** sửa trực tiếp state. Luôn tạo **bản sao mới**.

```jsx
// ❌ SAI
user.name = "ABC";
setUser(user);

// ✅ ĐÚNG
setUser({ ...user, name: "ABC" });
```

### useState với Array:

```jsx
function TodoList() {
  const [items, setItems] = useState(["Học React", "Làm bài tập"]);

  // Thêm item
  const addItem = (newItem) => {
    setItems([...items, newItem]); // Spread + thêm mới ở cuối
  };

  // Xóa item
  const removeItem = (index) => {
    setItems(items.filter((_, i) => i !== index)); // Lọc bỏ item tại index
  };

  // Cập nhật item
  const updateItem = (index, newValue) => {
    setItems(items.map((item, i) => (i === index ? newValue : item)));
  };

  return (
    <ul>
      {items.map((item, index) => (
        <li key={index}>
          {item}
          <button onClick={() => removeItem(index)}>Xóa</button>
        </li>
      ))}
    </ul>
  );
}
```

---

## 12. useEffect - Side Effects

### Side Effect là gì?

**Side Effect** = những hành động "phụ" ngoài việc render UI:

- Gọi API lấy dữ liệu
- Đặt/xóa timer (setTimeout, setInterval)
- Thao tác DOM trực tiếp
- Đăng ký/hủy event listener

### Cú pháp:

```jsx
import { useEffect } from "react";

useEffect(() => {
  // Code side effect ở đây
}, [dependencies]);
```

### 3 cách dùng useEffect:

#### Cách 1: Chạy sau MỖI lần render (không có dependency array)

```jsx
useEffect(() => {
  console.log("Component vừa render");
});
// → Chạy sau render đầu tiên VÀ sau mỗi lần re-render
// → HIẾM KHI DÙNG vì chạy quá nhiều
```

#### Cách 2: Chạy 1 LẦN DUY NHẤT khi mount (dependency array rỗng `[]`)

```jsx
useEffect(() => {
  console.log("Component mount - chạy 1 lần duy nhất");
  // Thường dùng để: gọi API lấy data
}, []);
// → [] = không phụ thuộc gì → chỉ chạy khi component mount (xuất hiện)
```

**ĐÂY LÀ CÁCH DÙNG PHỔ BIẾN NHẤT** - Gọi API khi trang load:

```jsx
function ShopList() {
  const [shops, setShops] = useState([]);

  useEffect(() => {
    // Gọi API khi component hiện ra
    fetch("http://localhost:8080/myapp/shops")
      .then((res) => res.json())
      .then((data) => setShops(data));
  }, []); // [] = chỉ chạy 1 lần

  return (
    <ul>
      {shops.map((shop) => (
        <li key={shop.id}>{shop.name}</li>
      ))}
    </ul>
  );
}
```

#### Cách 3: Chạy khi dependency thay đổi

```jsx
const [searchTerm, setSearchTerm] = useState("");

useEffect(() => {
  console.log("searchTerm thay đổi thành:", searchTerm);
  // Có thể gọi API tìm kiếm ở đây
}, [searchTerm]);
// → Chạy khi mount VÀ mỗi khi searchTerm thay đổi
```

### Cleanup Function (Dọn dẹp):

```jsx
useEffect(() => {
  // Setup
  const timer = setInterval(() => {
    console.log("Tick");
  }, 1000);

  // Cleanup - chạy khi component bị gỡ bỏ (unmount)
  return () => {
    clearInterval(timer); // Dọn dẹp timer
  };
}, []);
```

### Hình dung luồng useEffect:

```
Component mount (lần đầu xuất hiện)
  → render UI
  → chạy useEffect

State thay đổi (ví dụ: setCount)
  → re-render UI
  → nếu dependency thay đổi → chạy cleanup cũ → chạy useEffect mới

Component unmount (biến mất khỏi trang)
  → chạy cleanup
```

---

## 13. Xử lý sự kiện (Event Handling)

### Cú pháp cơ bản:

```jsx
function App() {
  const handleClick = () => {
    alert("Bạn vừa click!");
  };

  return <button onClick={handleClick}>Click tôi</button>;
  //              ↑          ↑
  //              |          └── Tên hàm (KHÔNG có dấu () - truyền reference)
  //              └── Tên event (camelCase, không phải onclick)
}
```

### Các event phổ biến:

| Event          | Khi nào xảy ra   | Thường dùng với         |
| -------------- | ---------------- | ----------------------- |
| `onClick`      | Click chuột      | Button, div, a...       |
| `onChange`     | Giá trị thay đổi | Input, select, textarea |
| `onSubmit`     | Submit form      | Form                    |
| `onKeyDown`    | Nhấn phím        | Input                   |
| `onMouseEnter` | Chuột vào        | Div, span...            |
| `onMouseLeave` | Chuột ra         | Div, span...            |
| `onFocus`      | Focus vào        | Input                   |
| `onBlur`       | Mất focus        | Input                   |

### Truyền tham số vào Event Handler:

```jsx
function ShopList() {
  const handleDelete = (shopId) => {
    console.log("Xóa shop:", shopId);
  };

  return (
    <div>
      {/* ❌ SAI - Gọi luôn khi render */}
      <button onClick={handleDelete(1)}>Xóa</button>

      {/* ✅ ĐÚNG - Bọc trong arrow function */}
      <button onClick={() => handleDelete(1)}>Xóa</button>

      {/* Giải thích:
          onClick={handleDelete(1)} → gọi handleDelete(1) NGAY khi render
          onClick={() => handleDelete(1)} → tạo function mới, chỉ gọi khi CLICK
      */}
    </div>
  );
}
```

### Event Object:

```jsx
function SearchBox() {
  const handleChange = (event) => {
    // event = thông tin về sự kiện
    console.log(event.target.value); // event.target = thẻ HTML gây ra event
    //                  ↑               // .value = giá trị hiện tại của input
  };

  const handleSubmit = (event) => {
    event.preventDefault(); // Ngăn form reload trang (hành vi mặc định)
    console.log("Form submitted!");
  };

  return (
    <form onSubmit={handleSubmit}>
      <input onChange={handleChange} />
      <button type="submit">Tìm</button>
    </form>
  );
}
```

---

# PHẦN 4: RENDER & DANH SÁCH

---

## 14. Conditional Rendering

**Conditional Rendering** = hiển thị UI khác nhau dựa trên điều kiện.

### Cách 1: Dùng `if/else` (trước return)

```jsx
function Greeting({ isLoggedIn }) {
  if (isLoggedIn) {
    return <h1>Chào mừng trở lại!</h1>;
  }
  return <h1>Vui lòng đăng nhập</h1>;
}
```

### Cách 2: Dùng Ternary `? :` (trong JSX)

```jsx
function Greeting({ isLoggedIn }) {
  return (
    <div>
      {isLoggedIn ? <h1>Chào mừng trở lại!</h1> : <h1>Vui lòng đăng nhập</h1>}
    </div>
  );
}
```

| Phần                           | Ý nghĩa                                                                       |
| ------------------------------ | ----------------------------------------------------------------------------- |
| `{isLoggedIn ? (...) : (...)}` | Nếu `isLoggedIn` là `true` → hiện phần trước `:`, ngược lại hiện phần sau `:` |

### Cách 3: Dùng `&&` (hiện hoặc không hiện)

```jsx
function Notification({ count }) {
  return (
    <div>
      <h1>Thông báo</h1>
      {count > 0 && <p>Bạn có {count} thông báo mới</p>}
      {/*
        count > 0 đúng → hiện <p>
        count > 0 sai  → không hiện gì
      */}
    </div>
  );
}
```

**Cẩn thận với `&&`:**

```jsx
// ❌ BUG: Khi count = 0, sẽ hiện số 0 trên màn hình
{
  count && <p>Có {count} item</p>;
}

// ✅ ĐÚNG: So sánh tường minh
{
  count > 0 && <p>Có {count} item</p>;
}
```

### Cách 4: Ẩn component

```jsx
function App() {
  const [showModal, setShowModal] = useState(false);

  return (
    <div>
      <button onClick={() => setShowModal(true)}>Mở Modal</button>
      {showModal && (
        <div className="modal">
          <p>Nội dung modal</p>
          <button onClick={() => setShowModal(false)}>Đóng</button>
        </div>
      )}
    </div>
  );
}
```

---

## 15. Rendering Lists & Key

### Render danh sách với `.map()`:

```jsx
function FruitList() {
  const fruits = ["Táo", "Cam", "Chuối", "Xoài"];

  return (
    <ul>
      {fruits.map((fruit, index) => (
        <li key={index}>{fruit}</li>
      ))}
    </ul>
  );
}
```

### Giải thích `.map()`:

```javascript
// .map() = tạo mảng MỚI bằng cách biến đổi từng phần tử

const numbers = [1, 2, 3];
const doubled = numbers.map((n) => n * 2);
// doubled = [2, 4, 6]

// Trong React: biến đổi data → JSX
const fruits = ["Táo", "Cam"];
const listItems = fruits.map((fruit) => <li>{fruit}</li>);
// listItems = [<li>Táo</li>, <li>Cam</li>]
```

### Key là gì? Tại sao cần?

**`key`** = ID duy nhất cho mỗi phần tử trong danh sách, giúp React nhận biết phần tử nào thêm/xóa/thay đổi.

```jsx
// ❌ KHÔNG có key → Warning + performance kém
{
  items.map((item) => <li>{item.name}</li>);
}

// ⚠️ Dùng index làm key → OK nếu danh sách KHÔNG thay đổi thứ tự
{
  items.map((item, index) => <li key={index}>{item.name}</li>);
}

// ✅ TỐT NHẤT - Dùng ID duy nhất
{
  items.map((item) => <li key={item.id}>{item.name}</li>);
}
```

### Ví dụ thực tế - Render bảng:

```jsx
function ShopTable() {
  const [shops, setShops] = useState([
    { id: 1, name: "Tien Phong", type: "Science", owner: "Bitis'" },
    { id: 2, name: "Kim Dong", type: "Education", owner: "ABC" },
  ]);

  return (
    <table>
      <thead>
        <tr>
          <th>#</th>
          <th>Tên</th>
          <th>Loại</th>
          <th>Chủ</th>
        </tr>
      </thead>
      <tbody>
        {shops.map((shop, index) => (
          <tr key={shop.id}>
            <td>{index + 1}</td>
            <td>{shop.name}</td>
            <td>{shop.type}</td>
            <td>{shop.owner}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
```

---

## 16. Form & Controlled Components

### Uncontrolled vs Controlled:

```
Uncontrolled: DOM quản lý giá trị input (HTML thuần)
  → Dùng ref để lấy giá trị khi cần

Controlled: React quản lý giá trị input (qua state)
  → Mỗi lần gõ → state update → input update
  → CÁCH DÙNG CHUẨN TRONG REACT
```

### Controlled Component:

```jsx
function LoginForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault(); // Ngăn reload trang
    console.log("Login:", username, password);
    // Gọi API login ở đây
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Username:</label>
        <input
          type="text"
          value={username} // Giá trị = state
          onChange={(e) => setUsername(e.target.value)} // Khi gõ → update state
        />
      </div>
      <div>
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <button type="submit">Đăng nhập</button>
    </form>
  );
}
```

### Luồng hoạt động:

```
1. User gõ chữ "a" vào input username
2. Event onChange xảy ra → e.target.value = "a"
3. setUsername("a") được gọi → state username = "a"
4. Component re-render → input hiển thị value="a"
```

### Các loại input:

```jsx
function AllInputTypes() {
  const [text, setText] = useState("");
  const [number, setNumber] = useState(0);
  const [selected, setSelected] = useState("");
  const [checked, setChecked] = useState(false);
  const [textarea, setTextarea] = useState("");

  return (
    <form>
      {/* Text Input */}
      <input
        type="text"
        value={text}
        onChange={(e) => setText(e.target.value)}
      />

      {/* Number Input */}
      <input
        type="number"
        value={number}
        onChange={(e) => setNumber(Number(e.target.value))}
      />

      {/* Select (Dropdown) */}
      <select value={selected} onChange={(e) => setSelected(e.target.value)}>
        <option value="">-- Chọn --</option>
        <option value="A">Lựa chọn A</option>
        <option value="B">Lựa chọn B</option>
      </select>

      {/* Checkbox */}
      <input
        type="checkbox"
        checked={checked}
        onChange={(e) => setChecked(e.target.checked)} // Dùng .checked thay .value
      />

      {/* Textarea */}
      <textarea
        value={textarea}
        onChange={(e) => setTextarea(e.target.value)}
      />
    </form>
  );
}
```

---

# PHẦN 5: GIAO TIẾP API

---

## 17. Gọi API với axios

### axios là gì?

**axios** = thư viện JavaScript để gửi **HTTP request** (gọi API).

### Cài đặt:

```bash
npm install axios
```

### So sánh axios vs fetch:

|                | axios                             | fetch (có sẵn)           |
| -------------- | --------------------------------- | ------------------------ |
| Cài đặt        | Cần `npm install`                 | Có sẵn trong trình duyệt |
| Parse JSON     | Tự động (`res.data`)              | Phải gọi `res.json()`    |
| Error handling | Tự throw error khi status 4xx/5xx | Không throw error        |
| Interceptors   | Có                                | Không                    |
| Cancel request | Có                                | Phức tạp                 |

### Các HTTP Method:

| Method     | Ý nghĩa          | Ví dụ              | axios                    |
| ---------- | ---------------- | ------------------ | ------------------------ |
| **GET**    | Lấy dữ liệu      | Lấy danh sách shop | `axios.get(url)`         |
| **POST**   | Tạo mới          | Tạo shop mới       | `axios.post(url, data)`  |
| **PUT**    | Cập nhật toàn bộ | Sửa shop           | `axios.put(url, data)`   |
| **PATCH**  | Cập nhật 1 phần  | Sửa 1 field        | `axios.patch(url, data)` |
| **DELETE** | Xóa              | Xóa shop           | `axios.delete(url)`      |

### Ví dụ cơ bản:

```javascript
import axios from "axios";

// GET - Lấy data
axios
  .get("http://localhost:8080/myapp/shops")
  .then((response) => {
    console.log(response.data); // Data từ server
    console.log(response.status); // 200
  })
  .catch((error) => {
    console.log(error.message); // Lỗi nếu có
  });

// POST - Gửi data
axios
  .post("http://localhost:8080/myapp/shops", {
    name: "Tien Phong",
    openTime: 8,
    owner: "Bitis",
    type: "Science",
  })
  .then((response) => {
    console.log("Tạo thành công:", response.data);
  });

// DELETE - Xóa
axios.delete("http://localhost:8080/myapp/shops/1").then(() => {
  console.log("Đã xóa");
});
```

### axios response object:

```javascript
axios.get(url).then((response) => {
  response.data; // ← DATA từ server (phần ta cần)
  response.status; // ← HTTP status code (200, 404, 500...)
  response.headers; // ← Response headers
});
```

### Xử lý lỗi:

```javascript
axios
  .post(url, data)
  .then((res) => {
    // Thành công (2xx)
    console.log(res.data);
  })
  .catch((err) => {
    // Thất bại (4xx, 5xx, network error)
    if (err.response) {
      // Server trả về lỗi (4xx, 5xx)
      console.log(err.response.status); // 400, 404, 500...
      console.log(err.response.data); // Error message từ server
    } else {
      // Network error (server không chạy, mất mạng...)
      console.log(err.message);
    }
  });
```

---

## 18. Kết hợp useState + useEffect + axios

Đây là pattern **phổ biến nhất** trong React: load data từ API khi trang mở.

### Pattern chuẩn:

```jsx
import { useState, useEffect } from "react";
import axios from "axios";

function ShopList() {
  // 1. State để lưu data
  const [shops, setShops] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 2. useEffect gọi API khi mount
  useEffect(() => {
    axios
      .get("http://localhost:8080/myapp/shops")
      .then((res) => {
        setShops(res.data); // Lưu data vào state
        setLoading(false); // Tắt loading
      })
      .catch((err) => {
        setError(err.message); // Lưu lỗi
        setLoading(false);
      });
  }, []); // [] = chạy 1 lần khi mount

  // 3. Render theo state
  if (loading) return <p>Đang tải...</p>;
  if (error) return <p>Lỗi: {error}</p>;

  return (
    <ul>
      {shops.map((shop) => (
        <li key={shop.id}>{shop.name}</li>
      ))}
    </ul>
  );
}
```

### Luồng hoạt động:

```
1. Component mount
   → shops = [], loading = true, error = null
   → Hiển thị: "Đang tải..."

2. useEffect chạy → axios.get gọi API
   → Chờ server trả về...

3a. API thành công:
   → setShops([...data...]), setLoading(false)
   → Re-render → Hiển thị danh sách shop

3b. API thất bại:
   → setError("..."), setLoading(false)
   → Re-render → Hiển thị "Lỗi: ..."
```

### Tách API ra file riêng (Service Pattern):

**Tạo `src/services/ShopService.js`:**

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

**Sử dụng trong Component:**

```jsx
import ShopService from "../services/ShopService";

function ShopList() {
  const [shops, setShops] = useState([]);

  useEffect(() => {
    ShopService.getAll().then((res) => setShops(res.data));
  }, []);

  // ...
}
```

### Tại sao tách ra Service?

| Không tách                        | Tách Service             |
| --------------------------------- | ------------------------ |
| URL API lặp lại ở nhiều component | URL chỉ viết 1 nơi       |
| Khó thay đổi URL sau này          | Sửa 1 chỗ = sửa tất cả   |
| Component dài, khó đọc            | Component ngắn, rõ ràng  |
| Khó test                          | Dễ mock service khi test |

---

# PHẦN 6: ROUTING

---

## 19. React Router Dom

### Routing là gì?

**Routing** = điều hướng giữa các "trang" trong SPA mà **không reload trình duyệt**.

```
URL: /           → Hiện component Home
URL: /about      → Hiện component About
URL: /shop/1     → Hiện component ShopDetail (với id=1)
```

### Cài đặt:

```bash
npm install react-router-dom
```

### Cấu hình cơ bản:

**`main.jsx` - Bọc BrowserRouter:**

```jsx
import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import App from "./App.jsx";

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>,
);
```

**`App.jsx` - Định nghĩa Routes:**

```jsx
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import About from "./pages/About";
import ShopDetail from "./pages/ShopDetail";
import NotFound from "./pages/NotFound";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="/shop/:id" element={<ShopDetail />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}
```

### Giải thích:

| Component         | Vai trò                                                                           |
| ----------------- | --------------------------------------------------------------------------------- |
| `<BrowserRouter>` | Bọc toàn bộ app, cung cấp context routing. **Phải bọc ở mức cao nhất** (main.jsx) |
| `<Routes>`        | Container chứa các Route, chỉ render Route khớp                                   |
| `<Route>`         | Định nghĩa 1 đường dẫn và component tương ứng                                     |

| Prop                 | Ý nghĩa                                                           |
| -------------------- | ----------------------------------------------------------------- |
| `path="/"`           | Khớp khi URL là `/` (trang chủ)                                   |
| `path="/about"`      | Khớp khi URL là `/about`                                          |
| `path="/shop/:id"`   | `:id` = tham số động. `/shop/1`, `/shop/2` đều khớp               |
| `path="*"`           | Wildcard - khớp với mọi URL không khớp các route trên (trang 404) |
| `element={<Home />}` | Component sẽ render khi URL khớp                                  |

### Link - Chuyển trang:

```jsx
import { Link } from "react-router-dom";

function Navigation() {
  return (
    <nav>
      <Link to="/">Trang chủ</Link> {/* Chuyển đến / */}
      <Link to="/about">Giới thiệu</Link> {/* Chuyển đến /about */}
      <Link to="/shop/1">Shop 1</Link> {/* Chuyển đến /shop/1 */}
    </nav>
  );
}
```

### Link vs thẻ `<a>`:

```jsx
// ❌ Dùng <a> → RELOAD toàn bộ trang (mất state, chậm)
<a href="/about">Giới thiệu</a>

// ✅ Dùng <Link> → Chỉ thay đổi component, KHÔNG reload
<Link to="/about">Giới thiệu</Link>
```

`<Link>` render thành `<a>` trong HTML nhưng React ngăn hành vi reload, thay vào đó chỉ đổi component.

---

## 20. useNavigate & useParams

### useNavigate - Chuyển trang bằng code

```jsx
import { useNavigate } from "react-router-dom";

function LoginPage() {
  const navigate = useNavigate();

  const handleLogin = () => {
    // Sau khi login thành công...
    navigate("/"); // Chuyển đến trang chủ
    // navigate('/shop/5')  // Chuyển đến /shop/5
    // navigate(-1)         // Quay lại trang trước (giống nút Back)
  };

  return <button onClick={handleLogin}>Đăng nhập</button>;
}
```

### Khi nào dùng `<Link>` vs `useNavigate`?

| Dùng `<Link>`                         | Dùng `useNavigate`                     |
| ------------------------------------- | -------------------------------------- |
| User **click để chuyển trang**        | Chuyển trang **sau khi xử lý logic**   |
| Hiển thị hyperlink trong UI           | Chuyển trang bằng code (programmatic)  |
| `<Link to="/about">Giới thiệu</Link>` | `navigate('/')` sau khi xóa thành công |

### useParams - Lấy tham số từ URL

```jsx
import { useParams } from "react-router-dom";

// Route: <Route path="/shop/:id" element={<ShopDetail />} />
// URL:   /shop/5

function ShopDetail() {
  const { id } = useParams(); // id = "5" (luôn là string)

  const [shop, setShop] = useState(null);

  useEffect(() => {
    // Gọi API lấy shop theo id
    axios
      .get(`http://localhost:8080/myapp/shops/${id}`)
      .then((res) => setShop(res.data));
  }, [id]); // Chạy lại nếu id thay đổi

  if (!shop) return <p>Loading...</p>;

  return (
    <div>
      <h1>{shop.name}</h1>
      <p>Owner: {shop.owner}</p>
    </div>
  );
}
```

### Giải thích useParams:

```
Route:  path="/shop/:id"
                     ↑ Khai báo tham số "id"

URL:    /shop/5
              ↑ Giá trị của "id" = "5"

Code:   const { id } = useParams()  → id = "5"
```

Nhiều tham số:

```
Route:  path="/category/:catId/product/:prodId"
URL:    /category/3/product/42

const { catId, prodId } = useParams()
// catId = "3", prodId = "42"
```

---

# PHẦN 7: UI FRAMEWORK

---

## 21. React-Bootstrap

### React-Bootstrap là gì?

**React-Bootstrap** = Bootstrap được viết lại thành **React Component**.

```
Bootstrap thuần: dùng class CSS
  <button class="btn btn-primary">Click</button>

React-Bootstrap: dùng Component + Props
  <Button variant="primary">Click</Button>
```

### Cài đặt:

```bash
npm install react-bootstrap bootstrap
```

### Import CSS (trong `main.jsx`):

```jsx
import "bootstrap/dist/css/bootstrap.min.css";
```

→ **BẮT BUỘC** dòng này. Nếu thiếu, component sẽ không có style.

### Các Component hay dùng:

#### Container, Row, Col (Layout)

```jsx
import { Container, Row, Col } from "react-bootstrap";

function Layout() {
  return (
    <Container>
      {" "}
      {/* Giống <div class="container"> */}
      <Row>
        {" "}
        {/* Giống <div class="row"> */}
        <Col sm={4}>Cột 1</Col> {/* Chiếm 4/12 cột */}
        <Col sm={8}>Cột 2</Col> {/* Chiếm 8/12 cột */}
      </Row>
    </Container>
  );
}
```

Bootstrap Grid = 12 cột. `sm={4}` = chiếm 4/12 = 1/3 chiều rộng.

#### Button

```jsx
import { Button } from 'react-bootstrap'

<Button variant="primary">Xanh dương</Button>   {/* Nền xanh */}
<Button variant="danger">Đỏ</Button>             {/* Nền đỏ */}
<Button variant="success">Xanh lá</Button>       {/* Nền xanh lá */}
<Button variant="secondary">Xám</Button>          {/* Nền xám */}
<Button variant="outline-primary">Viền xanh</Button>  {/* Chỉ viền */}
<Button variant="link">Link</Button>               {/* Trông như link */}
<Button size="sm">Nhỏ</Button>                     {/* Kích thước nhỏ */}
<Button size="lg">Lớn</Button>                     {/* Kích thước lớn */}
```

#### Table

```jsx
import { Table } from "react-bootstrap";

<Table bordered hover striped>
  {/* bordered = có viền
      hover = highlight khi rê chuột
      striped = dòng chẵn/lẻ màu khác nhau */}
  <thead>
    <tr>
      <th>#</th>
      <th>Tên</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>Nguyễn Văn A</td>
    </tr>
  </tbody>
</Table>;
```

#### Form

```jsx
import { Form, Button, Row, Col } from "react-bootstrap";

function MyForm() {
  const [name, setName] = useState("");

  return (
    <Form>
      <Form.Group as={Row} className="mb-3">
        {/* as={Row} = render Form.Group thành <div class="row"> */}

        <Form.Label column sm={2}>
          Tên:
        </Form.Label>
        {/* column = label thẳng hàng với input
            sm={2} = chiếm 2/12 cột */}

        <Col sm={10}>
          <Form.Control
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Nhập tên"
          />
          {/* Form.Control = <input> hoặc <textarea> */}
        </Col>
      </Form.Group>

      {/* Dropdown */}
      <Form.Group>
        <Form.Select onChange={(e) => console.log(e.target.value)}>
          <option value="">-- Chọn --</option>
          <option value="A">Lựa chọn A</option>
          <option value="B">Lựa chọn B</option>
        </Form.Select>
      </Form.Group>

      <Button type="submit">Gửi</Button>
    </Form>
  );
}
```

#### Modal (Popup)

```jsx
import { Modal, Button } from "react-bootstrap";

function App() {
  const [show, setShow] = useState(false);

  return (
    <div>
      <Button onClick={() => setShow(true)}>Mở Modal</Button>

      <Modal show={show} onHide={() => setShow(false)} centered>
        {/* show = hiện/ẩn modal
            onHide = gọi khi click ngoài hoặc nút X
            centered = canh giữa màn hình */}

        <Modal.Header closeButton>
          {/* closeButton = nút X góc phải */}
          <Modal.Title>Tiêu đề</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <p>Nội dung modal ở đây</p>
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShow(false)}>
            Đóng
          </Button>
          <Button variant="primary" onClick={() => alert("OK!")}>
            Xác nhận
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}
```

### Bootstrap className phổ biến:

| Class                     | Ý nghĩa                            |
| ------------------------- | ---------------------------------- |
| `mt-3`                    | margin-top: 1rem (3 = cấp độ, 1-5) |
| `mb-3`                    | margin-bottom                      |
| `ms-3`                    | margin-start (left)                |
| `me-3`                    | margin-end (right)                 |
| `p-3`                     | padding tất cả                     |
| `text-end`                | Căn văn bản sang phải              |
| `text-center`             | Căn giữa                           |
| `d-flex`                  | Display flex                       |
| `justify-content-between` | Flex: space-between                |

---

# PHẦN 8: HOOKS NÂNG CAO

---

## 22. useRef

### useRef dùng để:

1. **Truy cập DOM element** trực tiếp
2. **Lưu giá trị** mà KHÔNG gây re-render

### Truy cập DOM:

```jsx
import { useRef } from "react";

function TextInput() {
  const inputRef = useRef(null);
  //                      ↑ Giá trị ban đầu

  const handleClick = () => {
    inputRef.current.focus(); // Focus vào input
    inputRef.current.value; // Lấy giá trị DOM
  };

  return (
    <div>
      <input ref={inputRef} type="text" />
      {/*       ↑ Gắn ref vào element */}
      <button onClick={handleClick}>Focus Input</button>
    </div>
  );
}
```

### Lưu giá trị (không gây re-render):

```jsx
function Timer() {
  const [count, setCount] = useState(0);
  const intervalRef = useRef(null);

  const start = () => {
    intervalRef.current = setInterval(() => {
      setCount((c) => c + 1);
    }, 1000);
  };

  const stop = () => {
    clearInterval(intervalRef.current); // Dùng ref để lưu timer ID
  };

  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={start}>Start</button>
      <button onClick={stop}>Stop</button>
    </div>
  );
}
```

### useRef vs useState:

|              | useState                   | useRef                                |
| ------------ | -------------------------- | ------------------------------------- |
| Khi thay đổi | **Re-render** component    | **KHÔNG re-render**                   |
| Dùng khi     | Data cần hiển thị trên UI  | Data nội bộ, không hiển thị           |
| Ví dụ        | Danh sách shop, form input | Timer ID, DOM element, previous value |

---

## 23. useMemo & useCallback

### useMemo - Tối ưu tính toán nặng

```jsx
import { useMemo } from "react";

function ExpensiveComponent({ items, filter }) {
  // ❌ KHÔNG TỐI ƯU - tính lại MỖI lần render
  const filteredItems = items.filter((item) => item.name.includes(filter));

  // ✅ TỐI ƯU - chỉ tính lại khi items hoặc filter thay đổi
  const filteredItems = useMemo(() => {
    return items.filter((item) => item.name.includes(filter));
  }, [items, filter]);
  //    ↑ Dependencies - chỉ tính lại khi các giá trị này thay đổi

  return (
    <ul>
      {filteredItems.map((item) => (
        <li key={item.id}>{item.name}</li>
      ))}
    </ul>
  );
}
```

### useCallback - Tối ưu function

```jsx
import { useCallback } from "react";

function Parent() {
  const [count, setCount] = useState(0);

  // ❌ Mỗi lần render → tạo function MỚI → Child re-render không cần thiết
  const handleClick = () => console.log("Click");

  // ✅ Chỉ tạo function mới khi dependencies thay đổi
  const handleClick = useCallback(() => {
    console.log("Click");
  }, []); // [] = function không bao giờ thay đổi

  return <Child onClick={handleClick} />;
}
```

### Khi nào dùng?

|               | Dùng khi                                                 | KHÔNG dùng khi     |
| ------------- | -------------------------------------------------------- | ------------------ |
| `useMemo`     | Tính toán nặng (filter lớn, sort lớn)                    | Tính toán đơn giản |
| `useCallback` | Truyền function cho component con đã tối ưu (React.memo) | Hầu hết trường hợp |

→ **Lời khuyên**: Không cần dùng nếu app chạy mượt. Chỉ tối ưu khi có vấn đề performance.

---

## 24. useContext - Chia sẻ state toàn cục

### Vấn đề: Prop Drilling

```
App → Navbar → UserMenu → UserAvatar
                                ↑
                    Cần user data nhưng phải truyền qua 3 tầng
```

### Giải pháp: Context = "biến toàn cục" cho React

**Bước 1: Tạo Context**

```jsx
// src/context/AuthContext.jsx
import { createContext, useState, useContext } from "react";

const AuthContext = createContext(); // Tạo "hộp" chứa data

// Provider = component bọc ngoài, cung cấp data
export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  const login = (userData) => setUser(userData);
  const logout = () => setUser(null);

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {/*                  ↑ Data chia sẻ cho tất cả component con */}
      {children}
    </AuthContext.Provider>
  );
}

// Custom hook để dùng context dễ hơn
export function useAuth() {
  return useContext(AuthContext);
}
```

**Bước 2: Bọc Provider ở mức cao**

```jsx
// main.jsx
import { AuthProvider } from "./context/AuthContext";

createRoot(document.getElementById("root")).render(
  <AuthProvider>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </AuthProvider>,
);
```

**Bước 3: Dùng ở BẤT KỲ component con nào**

```jsx
// Ở bất kỳ component nào, dù sâu bao nhiêu tầng
function UserAvatar() {
  const { user, logout } = useAuth(); // Lấy data từ context

  if (!user) return <p>Chưa đăng nhập</p>;

  return (
    <div>
      <span>Xin chào {user.name}</span>
      <button onClick={logout}>Đăng xuất</button>
    </div>
  );
}
```

### Khi nào dùng Context?

| Dùng Context            | KHÔNG cần Context               |
| ----------------------- | ------------------------------- |
| Theme (dark/light mode) | Props chỉ truyền 1-2 tầng       |
| User authentication     | Data chỉ dùng trong 1 component |
| Language (i18n)         |                                 |
| Shopping cart           |                                 |

---

## 25. useReducer - State phức tạp

### Khi nào dùng useReducer thay useState?

- State có **nhiều giá trị liên quan** (form phức tạp)
- **Logic cập nhật phức tạp** (nhiều case)
- Quen thuộc với **Redux pattern**

### Cú pháp:

```jsx
import { useReducer } from "react";

// 1. Định nghĩa reducer function
function reducer(state, action) {
  switch (action.type) {
    case "INCREMENT":
      return { ...state, count: state.count + 1 };
    case "DECREMENT":
      return { ...state, count: state.count - 1 };
    case "RESET":
      return { ...state, count: 0 };
    default:
      return state;
  }
}

// 2. Dùng trong component
function Counter() {
  const [state, dispatch] = useReducer(reducer, { count: 0 });
  //     ↑        ↑           ↑          ↑            ↑
  //     |        |           |          |            └── Initial state
  //     |        |           |          └── Reducer function
  //     |        |           └── Hook
  //     |        └── Hàm gửi action
  //     └── State hiện tại

  return (
    <div>
      <p>Count: {state.count}</p>
      <button onClick={() => dispatch({ type: "INCREMENT" })}>+</button>
      <button onClick={() => dispatch({ type: "DECREMENT" })}>-</button>
      <button onClick={() => dispatch({ type: "RESET" })}>Reset</button>
    </div>
  );
}
```

### Ví dụ thực tế - Form:

```jsx
function formReducer(state, action) {
  switch (action.type) {
    case "SET_FIELD":
      return { ...state, [action.field]: action.value };
    case "RESET":
      return { name: "", email: "", age: "" };
    default:
      return state;
  }
}

function UserForm() {
  const [form, dispatch] = useReducer(formReducer, {
    name: "",
    email: "",
    age: "",
  });

  const handleChange = (field, value) => {
    dispatch({ type: "SET_FIELD", field, value });
  };

  return (
    <form>
      <input
        value={form.name}
        onChange={(e) => handleChange("name", e.target.value)}
      />
      <input
        value={form.email}
        onChange={(e) => handleChange("email", e.target.value)}
      />
      <button type="button" onClick={() => dispatch({ type: "RESET" })}>
        Reset
      </button>
    </form>
  );
}
```

---

## 26. Custom Hooks

### Custom Hook = tách logic tái sử dụng ra hàm riêng

**Quy tắc**: Tên phải bắt đầu bằng **"use"** (useFetch, useForm, useAuth...).

### Ví dụ: useFetch - Hook gọi API

```jsx
// src/hooks/useFetch.js
import { useState, useEffect } from "react";
import axios from "axios";

function useFetch(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    axios
      .get(url)
      .then((res) => {
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, [url]);

  return { data, loading, error };
}

export default useFetch;
```

**Sử dụng:**

```jsx
function ShopList() {
  const {
    data: shops,
    loading,
    error,
  } = useFetch("http://localhost:8080/myapp/shops");

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <ul>
      {shops.map((shop) => (
        <li key={shop.id}>{shop.name}</li>
      ))}
    </ul>
  );
}

// Dùng lại cho component khác:
function TypeList() {
  const { data: types, loading } = useFetch(
    "http://localhost:8080/myapp/shops/types",
  );

  if (loading) return <p>Loading...</p>;

  return (
    <select>
      {types.map((t) => (
        <option key={t} value={t}>
          {t}
        </option>
      ))}
    </select>
  );
}
```

### Ví dụ: useForm - Hook quản lý form

```jsx
// src/hooks/useForm.js
import { useState } from "react";

function useForm(initialValues) {
  const [values, setValues] = useState(initialValues);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setValues((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => setValues(initialValues);

  return { values, handleChange, resetForm, setValues };
}

export default useForm;
```

**Sử dụng:**

```jsx
function RegisterForm() {
  const { values, handleChange, resetForm } = useForm({
    name: "",
    email: "",
    password: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(values); // { name: '...', email: '...', password: '...' }
    resetForm();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="name" value={values.name} onChange={handleChange} />
      <input name="email" value={values.email} onChange={handleChange} />
      <input name="password" value={values.password} onChange={handleChange} />
      <button type="submit">Đăng ký</button>
    </form>
  );
}
```

---

# PHẦN 9: PATTERNS NÂNG CAO

---

## 27. Component Composition

**Composition** = tổ hợp component nhỏ thành component lớn.

### Ví dụ: Dashboard

```jsx
// Thay vì 1 component khổng lồ:
function Dashboard() {
  return <div>{/* 500 dòng code... */}</div>;
}

// Chia nhỏ thành nhiều component:
function Dashboard() {
  return (
    <div>
      <DashboardHeader />
      <div className="d-flex">
        <Sidebar />
        <MainContent>
          <StatsCards />
          <RecentOrders />
          <Chart />
        </MainContent>
      </div>
      <DashboardFooter />
    </div>
  );
}
```

→ Mỗi component chỉ lo 1 việc. Dễ đọc, dễ test, dễ sửa.

---

## 28. Lifting State Up

### Vấn đề: 2 component cần chia sẻ state

```
       App
      /   \
   Child1  Child2
     ↑        ↑
     └── Cần dùng chung data ──┘
```

### Giải pháp: "Nâng" state lên component cha chung

```jsx
// ❌ SAI - Mỗi child có state riêng, không sync
function Child1() {
  const [count, setCount] = useState(0);
  // ...
}

function Child2() {
  const [count, setCount] = useState(0);
  // ...
}

// ✅ ĐÚNG - State ở cha, truyền xuống con qua props
function App() {
  const [count, setCount] = useState(0); // State ở đây

  return (
    <div>
      <Child1 count={count} onIncrement={() => setCount(count + 1)} />
      <Child2 count={count} />
    </div>
  );
}

function Child1({ count, onIncrement }) {
  return <button onClick={onIncrement}>Count: {count}</button>;
}

function Child2({ count }) {
  return <p>Count hiện tại: {count}</p>;
}
```

### Nguyên tắc:

- State nên nằm ở **component cha nhỏ nhất** mà tất cả component cần dùng đều là con của nó
- Truyền **data** xuống qua props
- Truyền **hàm update** xuống qua props (nếu con cần thay đổi data)

---

## 29. Error Boundaries

### Error Boundary = "bắt lỗi" cho component

Khi 1 component bị lỗi (crash), Error Boundary ngăn toàn bộ app crash.

```jsx
// ErrorBoundary.jsx (phải dùng Class Component - chưa có Hook tương đương)
import React from "react";

class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false };
  }

  static getDerivedStateFromError(error) {
    return { hasError: true };
  }

  componentDidCatch(error, errorInfo) {
    console.error("Error:", error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      return <h1>Đã xảy ra lỗi. Vui lòng thử lại.</h1>;
    }
    return this.props.children;
  }
}

// Sử dụng:
function App() {
  return (
    <ErrorBoundary>
      <ShopList /> {/* Nếu ShopList crash → hiện "Đã xảy ra lỗi" */}
    </ErrorBoundary>
  );
}
```

→ Đây là trường hợp DUY NHẤT cần Class Component trong React hiện đại.

---

# PHẦN 10: TỔNG KẾT

---

## 30. Tóm tắt toàn bộ kiến thức

### Sơ đồ kiến thức:

```
React
├── Cơ bản
│   ├── JSX (HTML trong JS)
│   ├── Component (mảnh ghép UI)
│   ├── Props (truyền data cha → con)
│   └── Rendering (map, conditional)
│
├── State & Hooks
│   ├── useState (quản lý data thay đổi)
│   ├── useEffect (gọi API, side effects)
│   ├── useRef (DOM, giá trị không render)
│   ├── useMemo / useCallback (tối ưu)
│   ├── useContext (state toàn cục)
│   └── useReducer (state phức tạp)
│
├── Routing
│   ├── BrowserRouter / Routes / Route
│   ├── Link (chuyển trang - hyperlink)
│   ├── useNavigate (chuyển trang - code)
│   └── useParams (lấy tham số URL)
│
├── API
│   ├── axios (HTTP client)
│   └── Pattern: useState + useEffect + axios
│
├── UI
│   └── React-Bootstrap (Table, Form, Modal, Button...)
│
└── Patterns
    ├── Component Composition (chia nhỏ)
    ├── Lifting State Up (nâng state)
    ├── Service Pattern (tách API)
    └── Custom Hooks (tái sử dụng logic)
```

### Quy trình phát triển 1 ứng dụng React:

```
1. Tạo project       → npm create vite@latest
2. Cài dependencies  → npm install react-bootstrap axios react-router-dom
3. Cấu hình          → vite.config.js, main.jsx (BrowserRouter, CSS)
4. Tạo Service       → services/XxxService.js (gọi API)
5. Tạo Components    → components/XxxList.jsx, XxxDetail.jsx
6. Cấu hình Route    → App.jsx (Routes)
7. Test              → npm run dev → mở browser test
8. Build             → npm run build → folder dist/
```

### Cheat Sheet - Cú pháp hay dùng:

```jsx
// === STATE ===
const [value, setValue] = useState(initialValue)
setValue(newValue)                    // Set giá trị mới
setValue(prev => prev + 1)           // Set dựa trên giá trị cũ
setValue({...value, key: newVal})     // Update object
setValue([...value, newItem])         // Thêm vào array

// === EFFECT ===
useEffect(() => { ... }, [])         // Chạy 1 lần khi mount
useEffect(() => { ... }, [dep])      // Chạy khi dep thay đổi
useEffect(() => { return () => {} }, [])  // Cleanup

// === ROUTING ===
<Route path="/" element={<Home />} />
<Route path="/item/:id" element={<Detail />} />
<Link to="/path">Text</Link>
const navigate = useNavigate()
navigate('/path')
const { id } = useParams()

// === API ===
axios.get(url).then(res => res.data)
axios.post(url, data).then(res => res.data)
axios.delete(url).then(() => ...)

// === JSX ===
{condition && <Component />}           // Hiện nếu true
{condition ? <A /> : <B />}            // A hoặc B
{items.map(item => <Item key={item.id} />)}  // Render danh sách

// === REACT-BOOTSTRAP ===
<Container>, <Row>, <Col sm={6}>
<Button variant="primary" onClick={fn}>
<Table bordered hover>
<Form.Control value={v} onChange={e => set(e.target.value)}>
<Form.Select value={v} onChange={e => set(e.target.value)}>
<Modal show={bool} onHide={fn} centered>
```

### Tips cuối cùng:

1. **Đặt tên rõ ràng**: `handleSubmit`, `loadShops`, `isLoading` - đọc tên biết nghĩa
2. **Mỗi component 1 file**: Không nhồi nhét nhiều component vào 1 file
3. **Tách logic ra Service/Hook**: Component chỉ lo hiển thị UI
4. **Key trong list**: Luôn dùng ID duy nhất, tránh index nếu list thay đổi
5. **Không mutate state**: Luôn tạo bản sao mới thay vì sửa trực tiếp
6. **useEffect dependency**: Luôn khai báo đầy đủ dependency
7. **Console là bạn**: `console.log()` là công cụ debug tốt nhất cho người mới

---

> **Chúc bạn học tốt React! Hãy thực hành nhiều - đó là cách học nhanh nhất.** 🚀
