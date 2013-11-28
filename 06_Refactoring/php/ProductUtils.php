<?php

namespace CodingDojo;

class ProductUtils {

    public function validateProducts($products) {
        $requiredFields = array(
            'price',
            'name',
            'description',
            'type',
        );
        
        $valid = true;
        foreach ($products as $rawProduct) {
            $fields = array_keys($rawProduct);
            foreach ($requiredFields as $requiredField) {
                if (!in_array($requiredField, $fields)) {
                    $valid = false;
                }
            }
        }
        return $valid;
    }
}