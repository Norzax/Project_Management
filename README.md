# Project Management

Ứng dụng Java Backend để quản lý dự án.

## Mô Tả

Project Management là một ứng dụng đơn giản được xây dựng trên nền tảng Java và Spring Boot. Ứng dụng cung cấp các tính năng cơ bản để quản lý các dự án cá nhân hoặc tổ chức.

## Sơ đồ ERD
[Thiết kế dự án](https://drive.google.com/file/d/1QIW9Akdt6hKP1Oe9twGT9sVH1lyzSkzY/view?usp=sharing)

## Hướng Dẫn Cài Đặt

### Yêu Cầu

- Java 17
- Maven
- MySQL

### Cài Đặt và Chạy

1. Clone repository:

    ```bash
    git clone https://github.com/Norzax/Project_Management.git
    ```

2. Cấu hình cơ sở dữ liệu trong `application.properties`.

3. Chạy ứng dụng:

    ```bash
    mvn spring-boot:run
    ```

## Các Nhánh Quan Trọng

- **master**: Nhánh chính, luôn ổn định và chứa phiên bản đã được phát hành.
- **develop**: Nhánh phát triển, được sử dụng cho việc phát triển mới, các tính năng chưa hoàn thiện.
- **feature/<tên-tính-năng>**: Nhánh cho từng tính năng cụ thể đang được phát triển.
- **hotfix/<tên-hotfix>**: Nhánh sửa lỗi nhanh chóng để fix các vấn đề cấp bách từ production.


## Cấu Trúc Thư Mục

- `/src`: Mã nguồn Java.
- `/src/main/resources`: Cấu hình ứng dụng và tài nguyên.
- `/pom.xml`: Tệp cấu hình Maven.

## Đóng Góp

Chúng tôi rất hoan nghênh sự đóng góp từ cộng đồng. Nếu bạn muốn đóng góp vào dự án, vui lòng 
[liên hệ qua email](mailto:giangbaoluan5@.com)

---
© 2023 GiangBaoLuan Project-Management.
