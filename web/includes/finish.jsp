<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Footer Example</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        footer {
            background-color: #007bff; /* Blue background */
            color: #ffffff;
            padding: 40px 0;
            margin-top: 50px;
        }
        footer .company-details .icon {
            margin-right: 10px;
        }
        footer .company-details .col {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
        footer .company-details .col span {
            font-size: 16px;
        }
        footer .company-details {
            text-align: center;
        }
        footer .company-details .row {
            justify-content: center;
        }
        footer .copyright {
            text-align: center;
            margin-top: 20px;
        }
        footer .contact {
            list-style: none;
            padding: 0;
            margin: 10px 0 0 0;
        }
        footer .contact li {
            display: inline;
            margin: 0 10px;
        }
        footer .contact a {
            color: #ffffff;
            font-size: 20px;
        }
        footer .contact a:hover {
            color: #d1ecf1; /* Lighten on hover */
        }
    </style>
</head>
<body>

    <!-- Footer Menu -->
    <footer id="footer">
        <div class="container">
            <div class="company-details">
                <div class="row">
                    <div class="col-md-4 col-sm-12">
                        <span class="icon fa fa-map-marker"></span>
                        <span>Address: Hoa Lac Hi-Tech Park, Hanoi, Vietnam</span>
                    </div>
                    <div class="col-md-4 col-sm-12">
                        <span class="icon fa fa-phone"></span>
                        <span>Telephone: 012345678</span>
                    </div>
                    <div class="col-md-4 col-sm-12">
                        <span class="icon fa fa-envelope"></span>
                        <span>Email: @fpt.edu.vn</span>
                    </div>
                </div>
            </div>
            <!-- Copyright Section -->
            <div class="copyright">
                <p>© All rights reserved | FBTComputer.</p>
                <ul class="contact">
                    <li>
                        <a href="#" class="fa fa-twitter"></a>
                    </li>
                    <li>
                        <a href="#" class="fa fa-facebook"></a>
                    </li>
                    <li>
                        <a href="#" class="fa fa-pinterest-p"></a>
                    </li>
                </ul>
            </div>
        </div>
    </footer>

</body>
</html>
