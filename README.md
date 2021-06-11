# manageCourseAppBE
Khi run app, table Role chưa có dữ liệu, do đó, cần nhập tay vào table Role 2 record: (1) column name: ADMIN, (2) column name: USER
Cần sign up 1 tài khoản mới để đăng nhập. Các tài khoản mới đăng ký đều có role USER
Chỉ có role ADMIN mới xem được màn hình add course, edit course, delete course. 
Để truy cập với role ADMIN, cần đổi một record bất kỳ tại table Student, cụ thể đổi column role_id thành role_id tương ứng với id của ADMIN tại table Role
