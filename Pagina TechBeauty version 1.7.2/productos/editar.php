<?php
include_once('../config/conexion.php');

$Id = $_POST['Id'];
$categoria = $_POST['categorias'];

$consulta = "SELECT id_categoria FROM categoria WHERE id_categoria = $categoria";
$resultado = mysqli_query($conexion, $consulta);

if (mysqli_num_rows($resultado) == 0) {
    echo "La categoría seleccionada no existe";
} else {

    
    $nombre = $_POST['nombre'];
    $precio = $_POST['precio'];
    $cantidad = $_POST['cantidad'];
    $descripcion = $_POST['mensaje'];
    $unidadMedida = $_POST['unidadMedida'];
    $referencia = $_POST['referencia'];


    $consulta = "UPDATE producto SET id_categoria='$categoria', nombre_producto='$nombre', precio_producto='$precio', cantidad_producto='$cantidad', descripcion_producto = '$descripcion', unidad_medida='$unidadMedida', referencia_producto='$referencia' WHERE id_producto='$Id';";

    $resultado = mysqli_query($conexion, $consulta);

    if ($resultado == true) {
        header("Location: listarProductos.php");
    }

}

?>