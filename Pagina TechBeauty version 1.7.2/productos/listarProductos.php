<!DOCTYPE html>
<html lang="es">

<head>
    <link rel="icon" type="image/png" href="img/logo_barra.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Productos</title>
    <link rel="stylesheet" href="../CSS/consultar_productos.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    <!-- Bootstrap y jQuery -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- DataTables.js -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.23/css/dataTables.bootstrap4.min.css">
    <script src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.23/js/dataTables.bootstrap4.min.js"></script>
</head>

<body>
    <header>
        <div class="menu-dashboard">
            <div class="top-menu">
                <div class="logo">
                    <img src="./Img/logo.png" alt="logo">
                    <span>TechBeauty</span>
                </div>
                <div class="toggle" onclick="menuDesplazar()">
                    <i class="bi bi-list"></i>
                </div>
            </div>

            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-person"></i>
                    <span>Usuario</span>
                </div>
            </div>
            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-bag-plus-fill"></i>
                    <a href="registrar_Producto.php">Registrar Productos</a>
                </div>
            </div>
            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-search"></i>
                    <a href="listarProductos.php">Buscar Productos</a>
                </div>
            </div>
            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-bag-check-fill"></i>
                    <span>Ventas Realizadas</span>
                </div>
            </div>
            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-card-text"></i>
                    <span>Datos Personales</span>
                </div>
            </div>
            <div class="menu">
                <div class="CS">
                    <div class="cerrarSesion">
                        <i class="bi bi-power"></i>
                        <span>Cerrar Sesión</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <div class="container">
        <div class="titulo">
            <h2>Productos Registrados</h2>
        </div>
        <br>
        <a class="boton" href="registrar_Producto.php">Añadir Nuevo</a>
        <a class="boton-e" href="informeProductos.php">Generar EXCEL</a>
        <br /><br><br>
        <div class="table-responsive">
            <table id="miTabla" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ID Categoria</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Descripcion</th>
                        <th>Unidad Medida</th>
                        <th>Referencia</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>

                    <?php

                    include_once('../config/conexion.php');

                    $consulta = "SELECT p.id_producto, c.nombre_categoria, p.nombre_producto, p.precio_producto, p.cantidad_producto, p.descripcion_producto, p.unidad_medida, p.referencia_producto FROM producto p INNER JOIN categoria c ON p.id_categoria = c.id_categoria;";

                    $resultado = mysqli_query($conexion, $consulta);

                    while ($filas = mysqli_fetch_array($resultado)) {
                    ?>
                        <tr>
                            <th><?php echo $filas['id_producto'] ?></th>
                            <th><?php echo $filas['nombre_categoria'] ?></th>
                            <th><?php echo $filas['nombre_producto'] ?></th>
                            <th><?php echo $filas['precio_producto'] ?></th>
                            <th><?php echo $filas['cantidad_producto'] ?></th>
                            <th><?php echo $filas['descripcion_producto'] ?></th>
                            <th><?php echo $filas['unidad_medida'] ?></th>
                            <th><?php echo $filas['referencia_producto'] ?></th>
                            <th>
                                <a class="editar" href="editarProducto.php?Id=<?php echo $filas['id_producto'] ?>">Editar</a>
                                <br><br>
                                <a class="eliminar" href="eliminarProducto.php?Id=<?php echo $filas['id_producto'] ?>">Eliminar</a>
                            </th>
                        </tr>
                    <?php
                    }
                    ?>
                </tbody>
            </table>


            <script>
                $(document).ready(function() {
                    $('#miTabla').DataTable({
                        searching: true,
                        lengthMenu: [3, 5, 10, 15, 20],
                        language: {
                            url: "//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json"
                        },
                        order: [
                            [0, 'asc']
                        ]
                    });
                });
            </script>

            <script src="../java_script/menu_inve.js"></script>