<?php
include_once('../config/conexion.php');


$id = $_REQUEST['Id'];

$consulta = "DELETE FROM producto WHERE id_producto = '$id';";
$resultado = mysqli_query($conexion, $consulta);

if ($resultado) {
    header('Location: listarProductos.php');
} else {
    echo "Error al eliminar el producto";
}


?>