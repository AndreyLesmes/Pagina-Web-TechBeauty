<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="../CSS/registrar_Producto.css">
    <link rel="icon" type="image/png" href="img/logo_barra.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    <title>Registrar Productos</title>
</head>

<body>
    <div class="container">
        <div class="menu-dashboard">
            <div class="top-menu">
                <div class="logo">
                    <img src="./Img/logo.png" alt="logo">
                    <span>TechBeauty</span>
                </div>
                <div class="toggle">
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
        <main>
            <div class="content-container">
                <h1>Registrar Productos</h1>
                <form class="registro_productos" action="agregarProducto.php" method="post">
                    <div class="registro">
                        <div class="box">
                            <label for="categoria">Categoria: </label>
                            <select class="categorias" name="categorias">
                                <option selected disabled>Seleccione la categoria </option>
                                <option value="1">Maquillaje</option>
                                <option value="2">Cuidado Facial</option>
                                <option value="3">Cuidado Corporal</option>
                                <option value="4">Esmaltes</option>
                                <option value="5">Cabello</option>
                            </select>
                            <br><br>
                            <label for="nombre">Nombre: </label><br>
                            <input class="control" type="text" name="nombre" placeholder="Escriba el nombre del producto: ">
                            <br><br>
                            <label for="Precio">Precio: </label><br>
                            <input class="control" type="number" name="precio" placeholder="Ingrese el Precio del producto: " required min="1" step="1">
                            <br><br>
                            <label for="cantidad">Cantidad: </label><br>
                            <input class="control" type="number" name="cantidad" placeholder="Ingrese la cantidad del producto: " required min="1" max="500" step="1">
                        </div>
                        <div class="box">
                            <label for="descripcion">Descripción: </label><br>
                            <textarea class="control" name="mensaje" rows="3" cols="25"></textarea>
                            <br><br>
                            <label for="und_med">Unidad de Medida: </label><br>
                            <input class="control" type="text" name="unidadMedida" placeholder="Ingrese la unidad de medida: " required>
                            <br><br>
                            <label for="referencia">Referencia: </label><br>
                            <input class="control" type="text" name="referencia" placeholder="Ingrese la referencia del producto: " required>
                            <br>
                            <input type="submit" name="submit" value="Agregar Producto">
                        </div>
                    </div>

                </form>
            </div>
        </main>
    </div>

    <script src="../java_script/menu_inve.js"></script>
</body>

</html>