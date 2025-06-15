# Terraform template to create a private S3 bucket for storing the tfstate file
resource "aws_s3_bucket" "tfstate" {
  bucket = "${var.project}-tfstate-${random_id.suffix.hex}"

  force_destroy = false

  tags = {
    Name        = "${var.project}-tfstate"
    Environment = var.environment
  }
}

resource "aws_s3_bucket_versioning" "tfstate" {
  bucket = aws_s3_bucket.tfstate.id
  versioning_configuration {
    status = "Enabled"
  }
}

resource "aws_s3_bucket_server_side_encryption_configuration" "tfstate" {
  bucket = aws_s3_bucket.tfstate.id
  rule {
    apply_server_side_encryption_by_default {
      sse_algorithm = "AES256"
    }
  }
}

resource "random_id" "suffix" {
  byte_length = 4
}

output "bucket_name" {
    description = "S3 Bucket created"
    value       = aws_s3_bucket.tfstate.bucket
    sensitive   = false
}