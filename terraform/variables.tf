variable "project" {
  description = "Project name for resource naming."
  type        = string
  default     = "misterarias"
}

variable "environment" {
  description = "Deployment environment (e.g., dev, prod)."
  type        = string
  default     = "dev"
}
