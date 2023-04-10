<?php

require_once 'vendor/autoload.php';

use Twig\Environment;
use Twig\Loader\FilesystemLoader;

$loader = new FilesystemLoader(__DIR__ . '/templates');
$twig = new Environment($loader);

if (isset($_GET['name'])) {
	$template = $twig->createTemplate("Hello {$_GET['name']}!");
    echo $template->render();
} else {
    echo $twig->render('index.html');
}